import { HttpErrorResponse, HttpInterceptorFn } from '@angular/common/http';
import { AuthService } from '../services/auth/auth.service';
import { inject } from '@angular/core';
import { Router } from '@angular/router';
import { catchError, switchMap, throwError } from 'rxjs';

export const jwtInterceptor: HttpInterceptorFn = (req, next) => {
  const auth = inject(AuthService);
  const router = inject(Router);

  const token = auth.token();
  const authRequest = token ? req.clone({ setHeaders: { Authorization: `Bearer ${token}` } }) : req;

  return next(authRequest).pipe(
    catchError((err: HttpErrorResponse) => {
      if (err.status === 401) {
        // refreshToken$ debe devolver un Observable que actualiza el token en AuthService
        return auth.refreshToken$().pipe(
          switchMap(() => {
            const t = auth.token();
            const retried = t ? req.clone({ setHeaders: { Authorization: `Bearer ${t}` } }) : req;
            return next(retried);
          }),
          catchError(e => {
            auth.logout();
            router.navigate(['/login']);
            return throwError(() => e);
          })
        );
      }

      return throwError(() => err);
    })
  );
};
