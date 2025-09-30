import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { JwtService } from '../services/jwt/jwt.service';

export const authGuard: CanActivateFn = (route, state) => {
  const jwt = inject(JwtService);
  const router = inject(Router);

  if (jwt.isAuthenticated()) {
    return true;
  } else {
    router.navigate(['/login']);
    return false;
  }
};
