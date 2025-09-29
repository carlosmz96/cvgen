import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterOutlet, RouterModule, Router } from '@angular/router';

import { NavigationComponent } from './components/navigation/navigation.component';
import { LoadingOverlayComponent } from './components/loading-overlay/loading-overlay.component';

@Component({
  selector: 'app-root',
  imports: [
    CommonModule,
    RouterOutlet,
    NavigationComponent,
    RouterModule,
    LoadingOverlayComponent
],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {

  constructor(
    private router: Router
  ) { }

  get isAuthenticated(): boolean {
    return this.router.url !== '/login' && this.router.url !== '/register';
  }

}
