import {Injectable} from '@angular/core';
import {NotificationService} from "./notification.service";
import {HttpErrorResponse} from "@angular/common/http";
import {Error, isError} from "../entities/shared/Error";

@Injectable({
  providedIn: 'root'
})
export class ErrorHandlerService {

  constructor(private notificationService: NotificationService) {
  }

  /**
   * Handle Http operation that failed.
   */

  public handleError(error: HttpErrorResponse) {
    let errorMessage = "An error occurred.";

    if (error.error instanceof ErrorEvent) {
      // client-side error
      errorMessage = error.error.message;
    } else {
      // server-side error
      if (isError(error.error)) {
        const backendError = error.error as Error
        errorMessage = `Code : ${backendError.code}\nMessage : ${backendError.message}\nDetails: ${backendError.details}`
      } else {
        errorMessage = `Status : ${error.status} Message : ${error.error.message}`
      }
    }
    this.notificationService.showError(errorMessage);
  }
}
