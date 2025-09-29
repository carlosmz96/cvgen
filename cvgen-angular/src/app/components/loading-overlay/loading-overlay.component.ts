import { CommonModule } from '@angular/common';
import { ProgressSpinnerModule } from 'primeng/progressspinner';
import { DialogModule } from 'primeng/dialog';
import { LoadingService } from './../../services/loading/loading.service';
import { Component, inject, computed } from '@angular/core';

@Component({
  selector: 'app-loading-overlay',
  imports: [
    DialogModule,
    ProgressSpinnerModule,
    CommonModule
  ],
  templateUrl: './loading-overlay.component.html',
  styleUrl: './loading-overlay.component.scss'
})
export class LoadingOverlayComponent {

  private loading = inject(LoadingService);
  visible = computed(() => this.loading.visible());
  message = computed(() => this.loading.message());

}
