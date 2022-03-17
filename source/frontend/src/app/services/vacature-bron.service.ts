import {Injectable} from '@angular/core';
import {environment} from "../../environments/environment";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {AuthService} from "./auth.service";
import {ErrorHandlerService} from "./error-handler.service";
import {NotificationService} from "./notification.service";
import {Observable} from "rxjs";
import {upload} from "../util/upload";
import {Werkzoekende} from "../entities/werkzoekende/werkzoekende";
import {Vacature} from "../entities/vacature/vacature";

@Injectable({
  providedIn: 'root'
})
export class VacatureBronService {

  private gatewayUrl = `${environment.gatewayUrl}`
  private oin: string = "";

  private httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json'})
  };


  constructor(
    private http: HttpClient,
    private authService: AuthService,
    private errorHandlerService: ErrorHandlerService,
    private notificationService: NotificationService
  ) {
    this.oin = authService.getOin();
  }

  uploadFile(vacatures: string | ArrayBuffer | null): Observable<any> {
    return this.http
      .post(this.gatewayUrl + '/vacature/lijst/' + this.oin, vacatures, {
        headers: new HttpHeaders({'Content-Type': 'application/json'}),
        reportProgress: true,
        observe: 'events',
      })
      .pipe(
        upload(this.notificationService)
      )
  }


  getAll() {
    return this.http.get<Vacature[]>(this.gatewayUrl + "/vacature/lijst/" + this.oin);

  }
}
