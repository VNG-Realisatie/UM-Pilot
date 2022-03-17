import {Component, OnInit} from '@angular/core';
import {Observable} from "rxjs";
import {AanvraagVacature} from "../../entities/vacature/aanvraagVacature";
import {ColDef, GridOptions} from "ag-grid-community";
import {dateFormatter} from "../../util/dateformatter";
import {VacatureBemiddelaarService} from "../../services/vacature-bemiddelaar.service";
import {BtnCellRenderer} from "../../buttons/button-cell-renderer/button-cell-renderer.component";

@Component({
  selector: 'app-vacature-vraagid-list',
  templateUrl: './vacature-vraagid-list.component.html',
  styleUrls: ['./vacature-vraagid-list.component.css']
})
export class VacatureVraagidListComponent implements OnInit {

  aanvraagVacatures?: Observable<AanvraagVacature[]>
  gridOptions!: GridOptions;


  columnDefs: ColDef[] = [
    {headerName: "Kenmerk aanvraag", field: 'aanvraagKenmerk', filter: true},
    {headerName: "Datum van aanvraag", field: 'creatieDatum', filter: true, valueFormatter: dateFormatter},
    {headerName: "Aantal resterende detailaanvragen", field: 'timesAccessLeft', filter: true},
    {
      headerName: "", cellRenderer: 'btnCellRenderer',
      cellRendererParams: (params: any) => {
        return {
          urlPath: "/vacature/vraagid-detail/" + params.data.vraagId,
          buttonText: "Bekijk vacatures"
        }
      }
    }
  ];

  constructor(private vacatureService: VacatureBemiddelaarService) {
  }

  ngOnInit(): void {
    this.aanvraagVacatures = this.vacatureService.getAllVraagId();

    this.gridOptions = <GridOptions>{
      defaultColDef: {
        editable: false,
        sortable: true
      },
      pagination: true,
      paginationAutoPageSize: true,
      frameworkComponents: {
        btnCellRenderer: BtnCellRenderer
      },
      onGridReady: (event) => event.api.sizeColumnsToFit()
    }
  }

}
