import {Injectable} from '@angular/core';
import {environment} from "../../environments/environment";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {AuthService} from "./auth.service";
import {Observable} from "rxjs";
import {InlineResponse200} from "../entities/vacature/inlineResponse200";
import {MatchesRequestGemeente} from "../entities/shared/matchesRequestGemeente";
import {AanvraagVacature} from "../entities/vacature/aanvraagVacature";
import {Vacature} from "../entities/vacature/vacature";

@Injectable({
  providedIn: 'root'
})
export class VacatureBemiddelaarService {

  private gatewayUrl = `${environment.gatewayUrl}`
  private oin: string = "";

  private httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json'})
  };

  constructor(private http: HttpClient,
              private authService: AuthService,
  ) {
    this.oin = authService.getOin();
  }

  getVraagId(id: string): Observable<AanvraagVacature> {
    return this.http.get<AanvraagVacature>(this.gatewayUrl + "/aanvraagvacature/" + this.oin + "/" + id)

  }

  getDetailVacature(vraagId: string, vumId: string): Observable<Vacature> {
    return this.http.get<Vacature>(this.gatewayUrl + "/aanvraagvacature/detail/" + this.oin + "/" + vraagId + "/" + vumId)

  }

  getAllVraagId(): Observable<AanvraagVacature[]> {
    return this.http.get<AanvraagVacature[]>(this.gatewayUrl + "/aanvraagvacature/lijst/" + this.oin)
  }

  requestVacatures(request: MatchesRequestGemeente): Observable<InlineResponse200> {
    return this.http.post(this.gatewayUrl + "/aanvraagvacature/" + this.oin, request)

  }
}
