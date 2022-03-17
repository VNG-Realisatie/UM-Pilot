import {Component, OnInit} from '@angular/core';
import {Observable} from "rxjs";
import {ColDef, GridOptions} from "ag-grid-community";
import {Werkzoekende} from "../../entities/werkzoekende/werkzoekende";
import {WerkzoekendeBronService} from "../../services/werkzoekende-bron.service";
import {BtnCellRendererMoreDetail} from "../../buttons/button-cell-renderer-more-details/button-cell-renderer-more-detail";
import {WerkzoekendePassService} from "../../services/werkzoekende-pass.service";

@Component({
  selector: 'app-werkzoekende-list',
  templateUrl: './werkzoekende-list.component.html',
  styleUrls: ['./werkzoekende-list.component.css']
})
export class WerkzoekendeListComponent implements OnInit {

  werkzoekenden?: Observable<Werkzoekende[]>
  gridOptions!: GridOptions;

  columnDefs: ColDef[] = [
    {headerName: "ID werkzoekende", field: 'idWerkzoekende', filter: true},
    {headerName: "Minimaal werkuren per week ", field: 'werktijden.aantalWerkurenPerWeekMinimaal', filter: true},
    {headerName: "Maximaal werkuren per week ", field: 'werktijden.aantalWerkurenPerWeekMaximaal', filter: true},
    {headerName: "Maximale reisafstand", field: 'mobiliteit.maximaleReisafstand', filter: true},
    {headerName: "Maximale reistijd", field: 'mobiliteit.maximaleReistijd', filter: true},
    {headerName: "Bemiddelingspostcode", field: 'mobiliteit.bemiddelingspostcode', filter: true},
    {
      headerName: "", cellRenderer: 'btnCellRendererMoreDetail',
      cellRendererParams: (params: any) => {
        return {
          urlPath: "/werkzoekende/id/" + params.data.idWerkzoekende,
          buttonText: "Meer detail",
          onClick: () => this.werkzoekendePassService.setWerkzoekende(params.data)
        }
      }
    }
  ];

  constructor(private werkzoekendeService: WerkzoekendeBronService,
              private werkzoekendePassService: WerkzoekendePassService) {
  }

  ngOnInit(): void {
    this.werkzoekenden = this.werkzoekendeService.getAll();

    this.gridOptions = <GridOptions>{
      defaultColDef: {
        editable: false,
        sortable: true
      },
      pagination: true,
      paginationAutoPageSize: true,
      frameworkComponents: {
        btnCellRendererMoreDetail: BtnCellRendererMoreDetail
      },
      onGridReady: (event) => event.api.sizeColumnsToFit()
    }
  }

}
