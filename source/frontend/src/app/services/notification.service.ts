import {Injectable, TemplateRef} from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class NotificationService {
  toasts: any[] = [];

  showSuccess(textOrTpl: string | TemplateRef<any>) {
    this.toasts.push(
      {textOrTpl, classname: 'bg-success text-light', delay: 10000}
    );
  }

  showError(textOrTpl: string | TemplateRef<any>) {
    this.toasts.push(
      {textOrTpl, classname: 'bg-danger text-light', delay: 15000}
    );
  }

  remove(toast: any) {
    this.toasts = this.toasts.filter(t => t !== toast);
  }

  getToasts() {
    return this.toasts;
  }
}
