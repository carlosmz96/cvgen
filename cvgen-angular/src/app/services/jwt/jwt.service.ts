import { Injectable } from '@angular/core';
import { jwtDecode } from 'jwt-decode';

type JwtPayload = {
  sub?: string;
  uid?: number | string;
  exp?: number;
  iat?: number;
  [k: string]: unknown;
};

@Injectable({
  providedIn: 'root'
})
export class JwtService {

  private storageKey = 'access_token';

  setToken(token: string) {
    localStorage.setItem(this.storageKey, token);
  }

  getToken(): string | null {
    return localStorage.getItem(this.storageKey);
  }

  clearToken() {
    localStorage.removeItem(this.storageKey);
  }

  decode(token: string | null = this.getToken()): JwtPayload | null {
    if (!token) return null;
    try {
      return jwtDecode<JwtPayload>(token);
    } catch {
      return null;
    }
  }

  getUsername(): string | null {
    return this.decode()?.sub ?? null;
  }

  getUserId(): string | number | null {
    return this.decode()?.uid ?? null;
  }

  getExpiration(): Date | null {
    const exp = this.decode()?.exp;
    return exp ? new Date(exp * 1000) : null;
  }

  isExpired(leewaySeconds = 0): boolean {
    const exp = this.getExpiration();
    if (!exp) return true;
    return Date.now() >= exp.getTime() - leewaySeconds * 1000;
  }

  isAuthenticated(): boolean {
    const token = this.getToken();
    return !!token && !this.isExpired();
  }

}
