import {Component, OnInit, TemplateRef} from '@angular/core';
import {NotificationService} from 'src/app/services/notification.service';

@Component({
  selector: 'app-toast-container',
  templateUrl: './toast-container.component.html',
  styleUrls: ['./toast-container.component.css'],
  host: {'[class.ngb-toasts]': 'true'}
})
export class ToastContainerComponent implements OnInit {


  constructor(public notificationService: NotificationService) {
  }

  ngOnInit(): void {
  }

  remove(toast: any) {
    this.notificationService.remove(toast)
  }

  isTemplate(toast: { textOrTpl: any; }) {
    return toast.textOrTpl instanceof TemplateRef;
  }


}
