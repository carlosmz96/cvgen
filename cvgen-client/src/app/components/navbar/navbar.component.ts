import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';
import { AuthService } from '../../services/auth/auth.service';
import { CommonModule } from '@angular/common';

import { ButtonModule } from 'primeng/button';

@Component({
  selector: 'app-navbar',
  imports: [
    RouterModule,
    CommonModule,
    ButtonModule
  ],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.scss'
})
export class NavbarComponent {

  constructor(
    private auth: AuthService
  ) { }

  isLoggedIn(): boolean {
    return this.auth.isLoggedIn();
  }

}
