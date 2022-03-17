import {Component, OnInit} from '@angular/core';
import {Observable} from "rxjs";
import {ColDef, GridOptions} from "ag-grid-community";
import {dateFormatter} from "../../util/dateformatter";
import {Vacature} from "../../entities/vacature/vacature";
import {VacatureBronService} from "../../services/vacature-bron.service";
import {VacaturePassService} from "../../services/vacature-pass.service";
import {BtnCellRendererMoreDetail} from "../../buttons/button-cell-renderer-more-details/button-cell-renderer-more-detail";

@Component({
  selector: 'app-vacature-list',
  templateUrl: './vacature-list.component.html',
  styleUrls: ['./vacature-list.component.css']
})
export class VacatureListComponent implements OnInit {

  vacatures?: Observable<Vacature[]>
  gridOptions!: GridOptions;


  columnDefs: ColDef[] = [
    {headerName: "ID vacature", field: 'idVacature', filter: true},
    {headerName: "Naam vacature", field: 'naamVacature', filter: true},
    {
      headerName: "Sluitingsdatum vacature",
      field: 'sluitingsDatumVacature',
      filter: true,
      valueFormatter: dateFormatter
    },
    {
      headerName: "Datum aanvang werkzaamheden",
      field: 'arbeidsVoorwaarden.datumAanvangWerkzaamheden',
      filter: true,
      valueFormatter: dateFormatter
    },
    {
      headerName: "Datum einde werkzaamheden",
      field: 'arbeidsVoorwaarden.datumEindeWerkzaamheden',
      filter: true,
      valueFormatter: dateFormatter
    },
    {headerName: "Salaris indicatie", field: 'arbeidsVoorwaarden.salarisIndicatie', filter: true},
    {
      headerName: "", cellRenderer: 'btnCellRendererMoreDetail', suppressSizeToFit: true,
      cellRendererParams: (params: any) => {
        return {
          urlPath: "/vacature/id/" + params.data.idVacature,
          buttonText: "Meer detail",
          onClick: () => this.vacaturePassService.setVacature(params.data)
        }
      }
    }
  ];

  constructor(private vacatureService: VacatureBronService,
              private vacaturePassService: VacaturePassService) {
  }

  ngOnInit(): void {
    this.vacatures = this.vacatureService.getAll();

    this.gridOptions = <GridOptions>{
      defaultColDef: {
        editable: false,
        resizable: true,
        sortable: true
      },
      pagination: true,
      paginationAutoPageSize: true,
      frameworkComponents: {
        btnCellRendererMoreDetail: BtnCellRendererMoreDetail
      },
      onGridReady: (event) => {
        event.api.sizeColumnsToFit();
      }
    }
  }

}
