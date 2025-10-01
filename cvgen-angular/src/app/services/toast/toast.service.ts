import { computed, Injectable, signal } from '@angular/core';
import { MessageService } from 'primeng/api';

@Injectable({
  providedIn: 'root'
})
export class ToastService {

  constructor(
    private messageService: MessageService
  ) {}

  show(
    severity: 'success' | 'info' | 'warn' | 'error',
    summary: string,
    detail: string,
    life: number = 3000,
    sticky: boolean = false
  ) {
    this.messageService.add({
      severity,
      summary,
      detail,
      life,
      sticky
    });
  }

  clear() {
    this.messageService.clear();
  }

}
