import { Component } from '@angular/core';
import { Router, RouterOutlet } from '@angular/router';
import { NavbarComponent } from "./components/navbar/navbar.component";
import { CommonModule, NgClass } from '@angular/common';
import { PrimeNG } from 'primeng/config';

const esLocale = {
  firstDayOfWeek: 1,
  dayNames: ['domingo','lunes','martes','miércoles','jueves','viernes','sábado'],
  dayNamesShort: ['dom','lun','mar','mié','jue','vie','sáb'],
  dayNamesMin: ['D','L','M','X','J','V','S'],
  monthNames: ['enero','febrero','marzo','abril','mayo','junio','julio','agosto','septiembre','octubre','noviembre','diciembre'],
  monthNamesShort: ['ene','feb','mar','abr','may','jun','jul','ago','sep','oct','nov','dic'],
  today: 'Hoy',
  clear: 'Limpiar',
  weekHeader: 'Sm',
  dateFormat: 'dd/mm/yy'
};

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, NavbarComponent, CommonModule, NgClass],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {

  showNavbar: boolean = true;

  constructor(
    private router: Router,
    private primengConfig: PrimeNG
  ) {
    this.primengConfig.setTranslation(esLocale);

    this.router.events.subscribe(() => {
      this.showNavbar = !['/login', '/register'].includes(router.url);
    });
  }

}
