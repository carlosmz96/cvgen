import { Injectable, signal, computed } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class LoadingService {

  private _visible = signal(false);
  private _message = signal('Cargando...');

  readonly visible = computed(() => this._visible());
  readonly message = computed(() => this._message());

  show(message = 'Cargando...') {
    this._message.set(message);
    this._visible.set(true);
  }

  hide() {
    this._visible.set(false);
  }

  reset() {
    this._visible.set(false);
  }

}
