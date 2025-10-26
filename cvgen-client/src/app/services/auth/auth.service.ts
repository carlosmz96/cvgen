import { computed, Injectable, signal } from '@angular/core';
import { environment } from '../../../environments/environment';
import { User } from '../../models/User';
import { HttpClient } from '@angular/common/http';
import { AuthResponse } from '../../models/AuthResponse';

import { jwtDecode } from 'jwt-decode';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private api = `${environment.apiUrl}/auth`;
  private _token = signal<string | null>(localStorage.getItem('token'));
  private _user = signal<User | null>(this.readUserFromToken(this._token()));

  token = computed(() => this._token());
  user = computed(() => this._user());
  isLoggedIn = computed(() => !!this._token());
  userRole = computed(() => this._user()?.role);

  constructor(private http: HttpClient) { }

  login(email: string, password: string) {
    return this.http.post<AuthResponse>(`${this.api}/login`, { email, password });
  }

  register(user: User) {
    return this.http.post<AuthResponse>(`${this.api}/register`, user);
  }

  saveSession(response: AuthResponse) {
    localStorage.setItem('token', response.token);
    this._token.set(response.token);
    this._user.set(this.readUserFromToken(response.token));
  }

  logout() {
    localStorage.removeItem('token');
    this._token.set(null);
    this._user.set(null);
  }

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

}
