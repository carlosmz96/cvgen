import { computed, Injectable, signal } from '@angular/core';
import { environment } from '../../../environments/environment';
import { User } from '../../models/User';
import { HttpClient } from '@angular/common/http';
import { AuthResponse } from '../../models/AuthResponse';

import { jwtDecode } from 'jwt-decode';
import { tap, throwError } from 'rxjs';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private api = `${environment.apiUrl}/auth`;
  private _token = signal<string | null>(localStorage.getItem('token'));
  private _refresh = signal<string | null>(localStorage.getItem('refresh_token'));
  private _user = signal<User | null>(this.readUserFromToken(this._token()));

  token = computed(() => this._token());
  user = computed(() => this._user());
  isLoggedIn = computed(() => !!this._token());
  userRole = computed(() => this._user()?.role);

  warnExpiring = signal(false);
  private warnTimer?: any;
  private refreshTimer?: any;

  constructor(
    private http: HttpClient,
    private router: Router
  ) {
    const token = this._token();
    if (token) {
      try {
        const { exp } = jwtDecode<any>(token) || {};
        if (!exp || Date.now() >= exp * 1000) {
          this.logout();
          this.router.navigate(['/login']);
        } else {
          this.scheduleFromToken(token);
        }
      } catch {
        this.logout();
        this.router.navigate(['/login']);
      }
    }
  }

  /**
   * Inicia sesión
   * @param email Correo electrónico
   * @param password Contraseña
   * @returns Respuesta de autenticación
   */
  login(email: string, password: string) {
    return this.http.post<AuthResponse>(`${this.api}/login`, { email, password });
  }

  /**
   * Registra el usuario
   * @param user Datos del usuario
   * @returns Respuesta de autenticación
   */
  register(user: User) {
    return this.http.post<AuthResponse>(`${this.api}/register`, user);
  }

  /**
   * Guarda la sesión
   * @param response Respuesta de autenticación
   */
  saveSession(response: AuthResponse) {
    localStorage.setItem('token', response.token);
    localStorage.setItem('refresh_token', response.refreshToken);
    this._token.set(response.token);
    this._refresh.set(response.refreshToken);
    this._user.set(this.readUserFromToken(response.token));
    this.scheduleFromToken(response.token);
    this.warnExpiring.set(false);
  }

  /**
   * Método encargado de refrescar el token
   * @returns Respuesta de autenticación
   */
  refreshToken$() {
    const rt = this._refresh();
    if (!rt) return throwError(() => new Error('No refresh token'));
    return this.http.post<AuthResponse>(`${this.api}/refresh`, { refreshToken: rt })
      .pipe(tap(r => this.saveSession(r)));
  }

  /**
   * Cierre de sesión
   */
  logout() {
    localStorage.removeItem('token');
    localStorage.removeItem('refresh_token');

    this._token.set(null);
    this._refresh.set(null);
    this._user.set(null);

    clearTimeout(this.warnTimer);
    clearTimeout(this.refreshTimer);
    this.warnExpiring.set(false);

    this.router.navigate(['/']); //inicio
  }

  /**
   * Obtención de datos del usuario desde el token
   * @param token Token en forma de cadena
   * @returns Datos del usuario
   */
  private readUserFromToken(token: string | null): User | null {
    if (!token) return null;
    try {
      const decoded: any = jwtDecode(token);

      const user: User = {
        id: decoded.sub,
        name: decoded.name,
        email: decoded.email,
        role: decoded.role
      }

      return user;
    } catch {
      return null;
    }
  }

  /**
   * Programación del token y avisos
   * @param token Token en cadena
   */
  private scheduleFromToken(token: string | null) {
    clearTimeout(this.warnTimer);
    clearTimeout(this.refreshTimer);
    this.warnExpiring.set(false);
    if (!token) return;

    const { exp } = jwtDecode<any>(token) || {};
    if (!exp) return;

    const now = Date.now();
    const expMs = exp * 1000;
    const skew = 5000; // anti desajuste reloj

    // Avisa cuando queden 5 min
    const warnDelay = Math.max(expMs - 5 * 60_000 - now - skew, 0);
    this.warnTimer = setTimeout(() => this.warnExpiring.set(true), warnDelay);

    // Auto-refresh mediante suscripción cuando queden 2 min
    const refreshDelay = Math.max(expMs - 2 * 60_000 - now - skew, 0);
    this.refreshTimer = setTimeout(() => {
      this.refreshToken$().subscribe({
        next: () => this.warnExpiring.set(false),
        error: () => this.warnExpiring.set(true) // si falla, muestra aviso
      });
    }, refreshDelay);

    // Si ya queda menos de 5 min, muestra aviso inmediato
    if (expMs - now <= 5 * 60_000) this.warnExpiring.set(true);
  }

}
