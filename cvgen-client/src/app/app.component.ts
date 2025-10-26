import { Component } from '@angular/core';
import { Router, RouterOutlet } from '@angular/router';
import { NavbarComponent } from "./components/navbar/navbar.component";
import { CommonModule, NgClass } from '@angular/common';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, NavbarComponent, CommonModule, NgClass],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {

  showNavbar: boolean = true;

  constructor(private router: Router) {
    this.router.events.subscribe(() => {
      this.showNavbar = !['/login', '/register'].includes(router.url);
    });
  }

}
