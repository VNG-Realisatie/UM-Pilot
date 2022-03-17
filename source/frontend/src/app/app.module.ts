import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {MatFormFieldModule} from '@angular/material/form-field';
import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {NgbDateParserFormatter, NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {NgbDateCustomParserFormatter} from './util/ngb-custom-formatter';
import {NavBarComponent} from './components/nav-bar/nav-bar.component';
import {OAuthModule} from 'angular-oauth2-oidc';
import {environment} from 'src/environments/environment';
import {WerkzoekendeImportComponent} from './components/werkzoekende-import/werkzoekende-import.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import {MatProgressBarModule} from '@angular/material/progress-bar';
import {MatCardModule} from '@angular/material/card';
import {ToastContainerComponent} from './components/toast-container/toast-container.component';
import {VacatureRequestComponent} from './components/vacature-request/vacature-request.component';
import {AgGridModule} from "ag-grid-angular";
import {BtnCellRenderer} from "./buttons/button-cell-renderer/button-cell-renderer.component";
import {FooterComponent} from './components/footer/footer.component';
import {HeaderComponent} from './components/header/header.component';
import {
  BasicInformationVacatureComponent
} from './forms/vacature/basic-information-vacature/basic-information-vacature.component';
import {ParentFormVacatureComponent} from './forms/vacature/parent-form-vacature/parent-form-vacature.component';
import {TrimFormcontrolDirective} from './util/trim-formcontrol.directive';
import {CalendarPickerComponent} from './forms-util/calendar-picker/calendar-picker.component';
import {SollicitatiewijzeFormComponent} from './forms/vacature/sollicitatiewijze-form/sollicitatiewijze-form.component';
import {SelectEnumComponent} from './forms-util/select-enum/select-enum.component';
import {SectorFormComponent} from './forms/vacature/sector-form/sector-form.component';
import {RemoveItemSvgComponent} from './buttons/remove-item-svg/remove-item-svg.component';
import {AddItemSvgComponent} from './buttons/add-item-svg/add-item-svg.component';
import {
  ArbeidsvoorwaardenFormComponent
} from './forms/vacature/arbeidsvoorwaarden-form/arbeidsvoorwaarden-form.component';
import {AbstractBaseFormComponent} from './forms/abstract-base-form/abstract-base-form.component';
import {ContractvormFormComponent} from './forms/shared/contractvorm-form/contractvorm-form.component';
import {BeroepFormComponent} from './forms/shared/beroep-form/beroep-form.component';
import {
  BeroepsnaamOngecodeerdFormComponent
} from './forms/shared/beroepsnaam-ongecodeerd-form/beroepsnaam-ongecodeerd-form.component';
import {
  BeroepsnaamGecodeerdFormComponent
} from './forms/shared/beroepsnaam-gecodeerd-form/beroepsnaam-gecodeerd-form.component';
import {WerkervaringFormComponent} from './forms/vacature/werkervaring-form/werkervaring-form.component';
import {RijbewijsFormComponent} from './forms/shared/rijbewijs-form/rijbewijs-form.component';
import {VervoermiddelFormComponent} from './forms/shared/vervoermiddel-form/vervoermiddel-form.component';
import {FlexibiliteitFormComponent} from './forms/shared/flexibiliteit-form/flexibiliteit-form.component';
import {WerktijdenFormComponent} from './forms/shared/werktijden-form/werktijden-form.component';
import {CursusFormComponent} from './forms/vacature/cursus-form/cursus-form.component';
import {OpleidingFormComponent} from './forms/vacature/opleiding-form/opleiding-form.component';
import {OpleidingsnaamFormComponent} from './forms/shared/opleidingsnaam-form/opleidingsnaam-form.component';
import {
  OpleidingsnaamGecodeerdFormComponent
} from './forms/shared/opleidingsnaam-gecodeerd-form/opleidingsnaam-gecodeerd-form.component';
import {
  OpleidingsnaamOngecodeerdFormComponent
} from './forms/shared/opleidingsnaam-ongecodeerd-form/opleidingsnaam-ongecodeerd-form.component';
import {
  GedragscompetentieFormComponent
} from './forms/shared/gedragscompetentie-form/gedragscompetentie-form.component';
import {VakvaardigheidFormComponent} from './forms/shared/vakvaardigheid-form/vakvaardigheid-form.component';
import {TaalbeheersingFormComponent} from './forms/shared/taalbeheersing-form/taalbeheersing-form.component';
import {TaalTypeaheadComponent} from './forms-util/taal-typeahead/taal-typeahead.component';
import {BtnCellRendererMoreDetail} from "./buttons/button-cell-renderer-more-details/button-cell-renderer-more-detail";
import {VacatureScreenComponent} from './components/vacature-screen/vacature-screen.component';
import {
  BasicInformationMatchComponent
} from './forms-match/vacature/basic-information-match/basic-information-match.component';
import {
  ArbeidsvoorwaardenMatchComponent
} from './forms-match/vacature/arbeidsvoorwaarden-match/arbeidsvoorwaarden-match.component';
import {FlexibiliteitMatchComponent} from './forms-match/shared/flexibiliteit-match/flexibiliteit-match.component';
import {WerktijdenMatchComponent} from './forms-match/shared/werktijden-match/werktijden-match.component';
import {BeroepMatchComponent} from './forms-match/vacature/beroep-match/beroep-match.component';
import {
  BeroepsnaamGecodeerdMatchComponent
} from './forms-match/shared/beroepsnaam-gecodeerd-match/beroepsnaam-gecodeerd-match.component';
import {
  BeroepsnaamOngecodeerdMatchComponent
} from './forms-match/shared/beroepsnaam-ongecodeerd-match/beroepsnaam-ongecodeerd-match.component';
import {SectorMatchComponent} from './forms-match/vacature/sector-match/sector-match.component';
import {ContractvormMatchComponent} from './forms-match/shared/contractvorm-match/contractvorm-match.component';
import {
  SollicitatiewijzeMatchComponent
} from './forms-match/vacature/sollicitatiewijze-match/sollicitatiewijze-match.component';
import {WerkervaringMatchComponent} from './forms-match/vacature/werkervaring-match/werkervaring-match.component';
import {VervoermiddelMatchComponent} from './forms-match/shared/vervoermiddel-match/vervoermiddel-match.component';
import {CursusMatchComponent} from './forms-match/vacature/cursus-match/cursus-match.component';
import {OpleidingMatchComponent} from './forms-match/vacature/opleiding-match/opleiding-match.component';
import {
  OpleidingsnaamGecodeerdMatchComponent
} from './forms-match/shared/opleidingsnaam-gecodeerd-match/opleidingsnaam-gecodeerd-match.component';
import {
  OpleidingsnaamOngecodeerdMatchComponent
} from './forms-match/shared/opleidingsnaam-ongecodeerd-match/opleidingsnaam-ongecodeerd-match.component';
import {
  GedragscompetentieMatchComponent
} from './forms-match/shared/gedragscompetentie-match/gedragscompetentie-match.component';
import {VakvaardigheidMatchComponent} from './forms-match/shared/vakvaardigheid-match/vakvaardigheid-match.component';
import {TaalbeheersingMatchComponent} from './forms-match/shared/taalbeheersing-match/taalbeheersing-match.component';
import {RijbewijsMatchComponent} from './forms-match/shared/rijbewijs-match/rijbewijs-match.component';
import {
  VervoermiddelMatchDetailedComponent
} from './forms-match-detailed/shared/vervoermiddel-match-detailed/vervoermiddel-match-detailed.component';
import {
  ArbeidsvoorwaardenMatchDetailedComponent
} from './forms-match-detailed/vacature/arbeidsvoorwaarden-match-detailed/arbeidsvoorwaarden-match-detailed.component';
import {
  SollicitatiewijzeMatchDetailedComponent
} from './forms-match-detailed/vacature/sollicitatiewijze-match-detailed/sollicitatiewijze-match-detailed.component';
import {
  BasicInformationMatchDetailedComponent
} from './forms-match-detailed/vacature/basic-information-match-detailed/basic-information-match-detailed.component';
import {
  WerkgeverMatchDetailedComponent
} from './forms-match-detailed/vacature/werkgever-match-detailed/werkgever-match-detailed.component';
import {
  VacatureScreenDetailedComponent
} from './components/vacature-screen-detailed/vacature-screen-detailed.component';
import {
  WebadresMatchDetailedComponent
} from './forms-match-detailed/shared/webadres-match-detailed/webadres-match-detailed.component';
import {
  AdreshoudingMatchDetailedComponent
} from './forms-match-detailed/vacature/adreshouding-match-detailed/adreshouding-match-detailed.component';
import {
  AdresNederlandMatchDetailedComponent
} from './forms-match-detailed/vacature/adres-nederland-match-detailed/adres-nederland-match-detailed.component';
import {
  AdresBuitenlandMatchDetailedComponent
} from './forms-match-detailed/vacature/adres-buitenland-match-detailed/adres-buitenland-match-detailed.component';
import {HttpErrorInterceptor} from './helpers/http-error-interceptor.component';
import {
  StraatadresMatchDetailedComponent
} from './forms-match-detailed/vacature/straatadres-match-detailed/straatadres-match-detailed.component';
import {
  PostbusadresMatchDetailedComponent
} from './forms-match-detailed/vacature/postbusadres-match-detailed/postbusadres-match-detailed.component';
import {
  AntwoordnummeradresMatchDetailedComponent
} from './forms-match-detailed/vacature/antwoordnummeradres-match-detailed/antwoordnummeradres-match-detailed.component';
import {
  StraatadresBuitenlandMatchDetailedComponent
} from './forms-match-detailed/vacature/straatadres-buitenland-match-detailed/straatadres-buitenland-match-detailed.component';
import {
  PostbusadresBuitenlandMatchDetailedComponent
} from './forms-match-detailed/vacature/postbusadres-buitenland-match-detailed/postbusadres-buitenland-match-detailed.component';
import {
  ContactpersoonMatchDetailedComponent
} from './forms-match-detailed/vacature/contactpersoon-match-detailed/contactpersoon-match-detailed.component';
import {
  TelefoonMatchDetailedComponent
} from './forms-match-detailed/shared/telefoon-match-detailed/telefoon-match-detailed.component';
import {
  EmailMatchDetailedComponent
} from './forms-match-detailed/shared/email-match-detailed/email-match-detailed.component';
import {WerkzoekendeRequestComponent} from './components/werkzoekende-request/werkzoekende-request.component';
import {VacatureVraagidListComponent} from './components/vacature-vraagid-list/vacature-vraagid-list.component';
import {GoBackSvgComponent} from './buttons/go-back-svg/go-back-svg.component';
import {DownloadSvgComponent} from './buttons/download-svg/download-svg.component';
import {VacatureVraagidDetailComponent} from './components/vacature-vraagid-detail/vacature-vraagid-detail.component';
import {RefreshSvgComponent} from './buttons/refresh-svg/refresh-svg.component';
import {
  WerkzoekendeVraagidListComponent
} from './components/werkzoekende-vraagid-list/werkzoekende-vraagid-list.component';
import {
  ParentFormWerkzoekendeComponent
} from './forms/werkzoekende/parent-form-werkzoekende/parent-form-werkzoekende.component';
import {
  BasicInformationWerkzoekendeComponent
} from './forms/werkzoekende/basic-information-werkzoekende/basic-information-werkzoekende.component';
import {VoorkeurslandFormComponent} from './forms/werkzoekende/voorkeursland-form/voorkeursland-form.component';
import {MobiliteitFormComponent} from './forms/werkzoekende/mobiliteit-form/mobiliteit-form.component';
import {
  ArbeidsmarktkwalificatieFormComponent
} from './forms/werkzoekende/arbeidsmarktkwalificatie-form/arbeidsmarktkwalificatie-form.component';
import {
  CursusWerkzoekendeFormComponent
} from './forms/werkzoekende/cursus-werkzoekende-form/cursus-werkzoekende-form.component';
import {
  OpleidingWerkzoekendeFormComponent
} from './forms/werkzoekende/opleiding-werkzoekende-form/opleiding-werkzoekende-form.component';
import {
  WerkervaringWerkzoekendeFormComponent
} from './forms/werkzoekende/werkervaring-werkzoekende-form/werkervaring-werkzoekende-form.component';
import {
  SectorWerkzoekendeFormComponent
} from './forms/werkzoekende/sector-werkzoekende-form/sector-werkzoekende-form.component';
import {
  BemiddelingsberoepWerkzoekendeFormComponent
} from './forms/werkzoekende/bemiddelingsberoep-werkzoekende-form/bemiddelingsberoep-werkzoekende-form.component';
import {
  WerkzoekendeVraagidDetailComponent
} from './components/werkzoekende-vraagid-detail/werkzoekende-vraagid-detail.component';
import {WerkzoekendeScreenComponent} from './components/werkzoekende-screen/werkzoekende-screen.component';
import {
  WerkzoekendeScreenDetailedComponent
} from './components/werkzoekende-screen-detailed/werkzoekende-screen-detailed.component';
import {
  BasicInformationWerkzoekendeMatchComponent
} from './forms-match/werkzoekende/basic-information-werkzoekende-match/basic-information-werkzoekende-match.component';
import {
  MobiliteitWerkzoekendeMatchComponent
} from './forms-match/werkzoekende/mobiliteit-match/mobiliteit-werkzoekende-match.component';
import {
  ArbeidsmarktkwalificatieMatchComponent
} from './forms-match/werkzoekende/arbeidsmarktkwalificatie-match/arbeidsmarktkwalificatie-match.component';
import {
  CursusWerkzoekendeMatchComponent
} from './forms-match/werkzoekende/cursus-werkzoekende-match/cursus-werkzoekende-match.component';
import {
  OpleidingWerkzoekendeMatchComponent
} from './forms-match/werkzoekende/opleiding-werkzoekende-match/opleiding-werkzoekende-match.component';
import {
  WerkervaringWerkzoekendeMatchComponent
} from './forms-match/werkzoekende/werkervaring-werkzoekende-match/werkervaring-werkzoekende-match.component';
import {
  VoorkeurslandMatchComponent
} from './forms-match/werkzoekende/voorkeursland-match/voorkeursland-match.component';
import {
  SectorMatchDetailedComponent
} from './forms-match-detailed/shared/sector-match-detailed/sector-match-detailed.component';
import {
  BemiddelingsberoepMatchComponent
} from './forms-match/werkzoekende/bemiddelingsberoep-match/bemiddelingsberoep-match.component';
import {
  BasicInformationWerkzoekendeDetailedComponent
} from './forms-match-detailed/werkzoekende/basic-information-werkzoekende-detailed/basic-information-werkzoekende-detailed.component';
import {
  EisAanWerkDetailedComponent
} from './forms-match-detailed/werkzoekende/eis-aan-werk-detailed/eis-aan-werk-detailed.component';
import {
  ArbeidsmarktkwalificatieDetailedComponent
} from './forms-match-detailed/werkzoekende/arbeidsmarktkwalificatie-detailed/arbeidsmarktkwalificatie-detailed.component';
import {
  InteresseDetailedComponent
} from './forms-match-detailed/werkzoekende/interesse-detailed/interesse-detailed.component';
import {
  CursusWerkzoekendeDetailedComponent
} from './forms-match-detailed/werkzoekende/cursus-werkzoekende-detailed/cursus-werkzoekende-detailed.component';
import {
  OpleidingWerkzoekendeDetailedComponent
} from './forms-match-detailed/werkzoekende/opleiding-werkzoekende-detailed/opleiding-werkzoekende-detailed.component';
import {
  WerkervaringWerkzoekendeDetailedComponent
} from './forms-match-detailed/werkzoekende/werkervaring-werkzoekende-detailed/werkervaring-werkzoekende-detailed.component';
import {
  ContactgegevensWerkzoekendeDetailedComponent
} from './forms-match-detailed/werkzoekende/contactgegevens-werkzoekende-detailed/contactgegevens-werkzoekende-detailed.component';
import {
  ContactpersoonWerkzoekendeDetailedComponent
} from './forms-match-detailed/werkzoekende/contactpersoon-werkzoekende-detailed/contactpersoon-werkzoekende-detailed.component';
import { VacatureImportComponent } from './components/vacature-import/vacature-import.component';
import { VacatureListComponent } from './components/vacature-list/vacature-list.component';
import { WerkzoekendeListComponent } from './components/werkzoekende-list/werkzoekende-list.component';
import { MpOpleidingsnaamOngecodeerdComponent } from './forms-match/vacature/mp-opleidingsnaam-ongecodeerd/mp-opleidingsnaam-ongecodeerd.component';
import { OpleidingDetailedComponent } from './forms-match-detailed/vacature/opleiding-detailed/opleiding-detailed.component';
import { ParentFormDetailedWerkzoekendeComponent } from './forms-match-detailed/werkzoekende/parent-form-detailed-werkzoekende/parent-form-detailed-werkzoekende.component';
import { ParentFormDetailedVacatureComponent } from './forms-match-detailed/vacature/parent-form-detailed-vacature/parent-form-detailed-vacature.component';
import { VacatureViewComponent } from './components/vacature-view/vacature-view.component';
import { WerkzoekendeViewComponent } from './components/werkzoekende-view/werkzoekende-view.component';
import { PdfSvgComponent } from './buttons/pdf-svg/pdf-svg.component';
import { ButtonCellRendererFetched } from './buttons/button-cell-renderer-fetched/button-cell-renderer-fetched.component';
import { RapportageScreenComponent } from './components/rapportage-screen/rapportage-screen.component';


@NgModule({
  declarations: [
    AppComponent,
    NavBarComponent,
    WerkzoekendeImportComponent,
    ToastContainerComponent,
    VacatureRequestComponent,
    BtnCellRenderer,
    BtnCellRendererMoreDetail,
    FooterComponent,
    HeaderComponent,
    BasicInformationVacatureComponent,
    ParentFormVacatureComponent,
    TrimFormcontrolDirective,
    CalendarPickerComponent,
    SollicitatiewijzeFormComponent,
    SelectEnumComponent,
    SectorFormComponent,
    RemoveItemSvgComponent,
    AddItemSvgComponent,
    ArbeidsvoorwaardenFormComponent,
    AbstractBaseFormComponent,
    ContractvormFormComponent,
    BeroepFormComponent,
    BeroepsnaamOngecodeerdFormComponent,
    BeroepsnaamGecodeerdFormComponent,
    WerkervaringFormComponent,
    RijbewijsFormComponent,
    VervoermiddelFormComponent,
    FlexibiliteitFormComponent,
    WerktijdenFormComponent,
    CursusFormComponent,
    OpleidingFormComponent,
    OpleidingsnaamFormComponent,
    OpleidingsnaamGecodeerdFormComponent,
    OpleidingsnaamOngecodeerdFormComponent,
    GedragscompetentieFormComponent,
    VakvaardigheidFormComponent,
    TaalbeheersingFormComponent,
    TaalTypeaheadComponent,
    VacatureScreenComponent,
    BasicInformationMatchComponent,
    ArbeidsvoorwaardenMatchComponent,
    FlexibiliteitMatchComponent,
    WerktijdenMatchComponent,
    BeroepMatchComponent,
    BeroepsnaamGecodeerdMatchComponent,
    BeroepsnaamOngecodeerdMatchComponent,
    SectorMatchComponent,
    ContractvormMatchComponent,
    SollicitatiewijzeMatchComponent,
    WerkervaringMatchComponent,
    VervoermiddelMatchComponent,
    CursusMatchComponent,
    OpleidingMatchComponent,
    OpleidingsnaamGecodeerdMatchComponent,
    OpleidingsnaamOngecodeerdMatchComponent,
    GedragscompetentieMatchComponent,
    VakvaardigheidMatchComponent,
    TaalbeheersingMatchComponent,
    RijbewijsMatchComponent,
    VervoermiddelMatchDetailedComponent,
    ArbeidsvoorwaardenMatchDetailedComponent,
    SollicitatiewijzeMatchDetailedComponent,
    BasicInformationMatchDetailedComponent,
    WerkgeverMatchDetailedComponent,
    VacatureScreenDetailedComponent,
    WebadresMatchDetailedComponent,
    AdreshoudingMatchDetailedComponent,
    AdresNederlandMatchDetailedComponent,
    AdresBuitenlandMatchDetailedComponent,
    StraatadresMatchDetailedComponent,
    PostbusadresMatchDetailedComponent,
    AntwoordnummeradresMatchDetailedComponent,
    StraatadresBuitenlandMatchDetailedComponent,
    PostbusadresBuitenlandMatchDetailedComponent,
    ContactpersoonMatchDetailedComponent,
    TelefoonMatchDetailedComponent,
    EmailMatchDetailedComponent,
    SectorMatchDetailedComponent,
    WerkzoekendeRequestComponent,
    VacatureVraagidListComponent,
    GoBackSvgComponent,
    DownloadSvgComponent,
    VacatureVraagidDetailComponent,
    RefreshSvgComponent,
    WerkzoekendeVraagidListComponent,
    ParentFormWerkzoekendeComponent,
    BasicInformationWerkzoekendeComponent,
    VoorkeurslandFormComponent,
    MobiliteitFormComponent,
    ArbeidsmarktkwalificatieFormComponent,
    CursusWerkzoekendeFormComponent,
    OpleidingWerkzoekendeFormComponent,
    WerkervaringWerkzoekendeFormComponent,
    SectorWerkzoekendeFormComponent,
    BemiddelingsberoepWerkzoekendeFormComponent,
    WerkzoekendeVraagidDetailComponent,
    WerkzoekendeScreenComponent,
    WerkzoekendeScreenDetailedComponent,
    BasicInformationWerkzoekendeMatchComponent,
    MobiliteitWerkzoekendeMatchComponent,
    ArbeidsmarktkwalificatieMatchComponent,
    CursusWerkzoekendeMatchComponent,
    OpleidingWerkzoekendeMatchComponent,
    WerkervaringWerkzoekendeMatchComponent,
    VoorkeurslandMatchComponent,
    SectorMatchDetailedComponent,
    BemiddelingsberoepMatchComponent,
    BasicInformationWerkzoekendeDetailedComponent,
    EisAanWerkDetailedComponent,
    ArbeidsmarktkwalificatieDetailedComponent,
    InteresseDetailedComponent,
    CursusWerkzoekendeDetailedComponent,
    OpleidingWerkzoekendeDetailedComponent,
    WerkervaringWerkzoekendeDetailedComponent,
    ContactgegevensWerkzoekendeDetailedComponent,
    ContactpersoonWerkzoekendeDetailedComponent,
    VacatureImportComponent,
    VacatureListComponent,
    WerkzoekendeListComponent,
    MpOpleidingsnaamOngecodeerdComponent,
    OpleidingDetailedComponent,
    ParentFormDetailedWerkzoekendeComponent,
    ParentFormDetailedVacatureComponent,
    VacatureViewComponent,
    WerkzoekendeViewComponent,
    PdfSvgComponent,
    ButtonCellRendererFetched,
    RapportageScreenComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    NgbModule,
    MatSnackBarModule,
    MatCardModule,
    MatProgressBarModule,
    OAuthModule.forRoot({
      resourceServer: {
        allowedUrls: [`${environment.gatewayUrl}`],
        sendAccessToken: true
      }
    }),
    BrowserAnimationsModule,
    AgGridModule.withComponents([BtnCellRenderer]),
  ],
  providers: [
    {provide: NgbDateParserFormatter, useClass: NgbDateCustomParserFormatter},
    {provide: HTTP_INTERCEPTORS, useClass: HttpErrorInterceptor, multi: true}
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
