import {Injectable} from '@angular/core';
import {environment} from "../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {AuthService} from "./auth.service";
import {Observable} from "rxjs";
import {AanvraagWerkzoekende} from "../entities/werkzoekende/aanvraagWerkzoekende";
import {MatchesRequestGemeente} from "../entities/shared/matchesRequestGemeente";
import {InlineResponse200} from "../entities/vacature/inlineResponse200";
import {Werkzoekende} from "../entities/werkzoekende/werkzoekende";

@Injectable({
  providedIn: 'root'
})
export class WerkzoekendeBemiddelaarService {

  private gatewayUrl = `${environment.gatewayUrl}`
  private oin: string = "";

  constructor(private http: HttpClient,
              private authService: AuthService) {
    this.oin = authService.getOin();
  }

  getAllVraagId(): Observable<AanvraagWerkzoekende[]> {
    return this.http.get<AanvraagWerkzoekende[]>(this.gatewayUrl + "/aanvraagwerkzoekende/lijst/" + this.oin);

  }

  requestVacatures(request: MatchesRequestGemeente): Observable<InlineResponse200> {
    return this.http.post(this.gatewayUrl + "/aanvraagwerkzoekende/" + this.oin, request);

  }

  getVraagId(vraagId: string): Observable<AanvraagWerkzoekende> {
    return this.http.get<AanvraagWerkzoekende>(this.gatewayUrl + "/aanvraagwerkzoekende/" + this.oin + "/" + vraagId);
  }


  getDetailVacature(vraagId: string, vumId: string): Observable<Werkzoekende> {
    return this.http.get<Werkzoekende>(this.gatewayUrl + "/aanvraagwerkzoekende/detail/" + this.oin + "/" + vraagId + "/" + vumId);
  }
}
