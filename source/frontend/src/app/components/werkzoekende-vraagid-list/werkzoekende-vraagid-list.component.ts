import {Component, OnInit} from '@angular/core';
import {Observable} from "rxjs";
import {ColDef, GridOptions} from "ag-grid-community";
import {dateFormatter} from "../../util/dateformatter";
import {BtnCellRenderer} from "../../buttons/button-cell-renderer/button-cell-renderer.component";
import {WerkzoekendeBemiddelaarService} from "../../services/werkzoekende-bemiddelaar.service";
import {AanvraagWerkzoekende} from "../../entities/werkzoekende/aanvraagWerkzoekende";

@Component({
  selector: 'app-werkzoekende-vraagid-list',
  templateUrl: './werkzoekende-vraagid-list.component.html',
  styleUrls: ['./werkzoekende-vraagid-list.component.css']
})
export class WerkzoekendeVraagidListComponent implements OnInit {

  aanvraagWerkzoekenden?: Observable<AanvraagWerkzoekende[]>
  gridOptions!: GridOptions;

  columnDefs: ColDef[] = [
    {headerName: "Kenmerk aanvraag", field: 'aanvraagKenmerk', filter: true},
    {headerName: "Datum van aanvraag", field: 'creatieDatum', filter: true, valueFormatter: dateFormatter},
    {headerName: "Aantal resterende detailaanvragen", field: 'timesAccessLeft', filter: true},
    {
      headerName: "", cellRenderer: 'btnCellRenderer',
      cellRendererParams: (params: any) => {
        return {
          urlPath: "/werkzoekende/vraagid-detail/" + params.data.vraagId,
          buttonText: "Bekijk werkzoekenden"
        }
      }
    }
  ];

  constructor(private werkzoekendeService: WerkzoekendeBemiddelaarService) {
  }

  ngOnInit(): void {
    this.aanvraagWerkzoekenden = this.werkzoekendeService.getAllVraagId();

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
