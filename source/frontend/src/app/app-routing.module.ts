import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {WerkzoekendeImportComponent} from './components/werkzoekende-import/werkzoekende-import.component';
import {AuthGuard} from './helpers/auth.guard';
import {VacatureRequestComponent} from "./components/vacature-request/vacature-request.component";
import {VacatureScreenComponent} from "./components/vacature-screen/vacature-screen.component";
import {
  VacatureScreenDetailedComponent
} from "./components/vacature-screen-detailed/vacature-screen-detailed.component";
import {WerkzoekendeRequestComponent} from "./components/werkzoekende-request/werkzoekende-request.component";
import {VacatureVraagidListComponent} from "./components/vacature-vraagid-list/vacature-vraagid-list.component";
import {VacatureVraagidDetailComponent} from "./components/vacature-vraagid-detail/vacature-vraagid-detail.component";
import {
  WerkzoekendeVraagidListComponent
} from "./components/werkzoekende-vraagid-list/werkzoekende-vraagid-list.component";
import {WerkzoekendeScreenComponent} from "./components/werkzoekende-screen/werkzoekende-screen.component";
import {
  WerkzoekendeVraagidDetailComponent
} from "./components/werkzoekende-vraagid-detail/werkzoekende-vraagid-detail.component";
import {
  WerkzoekendeScreenDetailedComponent
} from "./components/werkzoekende-screen-detailed/werkzoekende-screen-detailed.component";
import {VacatureImportComponent} from "./components/vacature-import/vacature-import.component";
import {VacatureListComponent} from "./components/vacature-list/vacature-list.component";
import {WerkzoekendeListComponent} from "./components/werkzoekende-list/werkzoekende-list.component";
import {WerkzoekendeViewComponent} from "./components/werkzoekende-view/werkzoekende-view.component";
import {VacatureViewComponent} from "./components/vacature-view/vacature-view.component";
import {RapportageScreenComponent} from "./components/rapportage-screen/rapportage-screen.component";

const routes: Routes = [
  {path: 'import-werkzoekenden', component: WerkzoekendeImportComponent, canActivate: [AuthGuard]},
  {path: 'request-werkzoekende', component: WerkzoekendeRequestComponent, canActivate: [AuthGuard]},
  {path: 'werkzoekende/list', component: WerkzoekendeListComponent, canActivate: [AuthGuard]},
  {path: 'werkzoekende/id/:id', component: WerkzoekendeViewComponent, canActivate: [AuthGuard]},
  {path: 'werkzoekende/vraagid-list', component: WerkzoekendeVraagidListComponent, canActivate: [AuthGuard]},
  {
    path: 'werkzoekende/vraagid-detail/:vraagId',
    component: WerkzoekendeVraagidDetailComponent,
    canActivate: [AuthGuard]
  },
  {path: 'werkzoekende/:vraagId/:vumId', component: WerkzoekendeScreenComponent, canActivate: [AuthGuard]},
  {
    path: 'werkzoekende/detail/:vraagId/:vumId',
    component: WerkzoekendeScreenDetailedComponent,
    canActivate: [AuthGuard]
  },

  {path: 'import-vacatures', component: VacatureImportComponent, canActivate: [AuthGuard]},
  {path: 'request-vacature', component: VacatureRequestComponent, canActivate: [AuthGuard]},
  {path: 'vacature/list', component: VacatureListComponent, canActivate: [AuthGuard]},
  {path: 'vacature/id/:id', component: VacatureViewComponent, canActivate: [AuthGuard]},
  {path: 'vacature/vraagid-list', component: VacatureVraagidListComponent, canActivate: [AuthGuard]},
  {path: 'vacature/vraagid-detail/:vraagId', component: VacatureVraagidDetailComponent, canActivate: [AuthGuard]},
  {path: 'vacature/:vraagId/:vumId', component: VacatureScreenComponent, canActivate: [AuthGuard]},
  {path: 'vacature/detail/:vraagId/:vumId', component: VacatureScreenDetailedComponent, canActivate: [AuthGuard]},

  {path: 'rapportage', component: RapportageScreenComponent, canActivate: [AuthGuard]},


  {path: '', redirectTo: '/import-werkzoekenden', pathMatch: 'full'}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
