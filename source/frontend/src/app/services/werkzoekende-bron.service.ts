import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {HttpClient} from '@angular/common/http';
import {environment} from 'src/environments/environment';
import {NotificationService} from './notification.service';
import {upload} from '../util/upload';
import {AuthService} from './auth.service';
import {ErrorHandlerService} from "./error-handler.service";
import {Werkzoekende} from "../entities/werkzoekende/werkzoekende";


@Injectable({
  providedIn: 'root'
})
export class WerkzoekendeBronService {

  private gatewayUrl = `${environment.gatewayUrl}`
  private oin: string = "";

  constructor(
    private http: HttpClient,
    private authService: AuthService,
    private errorHandlerService: ErrorHandlerService,
    private notificationService: NotificationService
  ) {
    this.oin = authService.getOin();
  }

  uploadFile(file: File): Observable<any> {
    const data = new FormData()
    data.append('file', file)
    return this.http
      .post(this.gatewayUrl + '/camel/upload/report/' + this.oin, data, {
        reportProgress: true,
        observe: 'events',
      })
      .pipe(
        upload(this.notificationService)
      )
  }

  getAll() {
    return this.http.get<Werkzoekende[]>(this.gatewayUrl + "/werkzoekende/lijst/" + this.oin);
  }
}

