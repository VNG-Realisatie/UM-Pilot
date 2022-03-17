import {Component, OnInit} from '@angular/core';
import {EMPTY, Observable} from "rxjs";
import {AanvraagVacature} from "../../entities/vacature/aanvraagVacature";
import {ColDef, GridApi, GridOptions} from "ag-grid-community";
import {dateFormatter} from "../../util/dateformatter";
import {ActivatedRoute} from "@angular/router";
import {Location} from "@angular/common";
import {VacatureBemiddelaarService} from "../../services/vacature-bemiddelaar.service";
import {BtnCellRendererMoreDetail} from "../../buttons/button-cell-renderer-more-details/button-cell-renderer-more-detail";
import {MPVacatureMatch} from "../../entities/vacature/mPVacatureMatch";
import {VacaturePassService} from "../../services/vacature-pass.service";
import {
  ButtonCellRendererFetched
} from "../../buttons/button-cell-renderer-fetched/button-cell-renderer-fetched.component";

@Component({
  selector: 'app-vacature-vraagid-detail',
  templateUrl: './vacature-vraagid-detail.component.html',
  styleUrls: ['./vacature-vraagid-detail.component.css']
})
export class VacatureVraagidDetailComponent implements OnInit {

  aanvraagVacature$: Observable<AanvraagVacature> = EMPTY;
  vraagId?: string | null;

  gridApi!: GridApi;
  gridOptions!: GridOptions;
  columnDefs: ColDef[] = [
    {headerName: "Vum ID", field: 'vumID', filter: true},
    {headerName: "Sluitingsdatum", field: 'sluitingsDatumVacature', filter: true, valueFormatter: dateFormatter},
    {
      headerName: "Datum aanvang werkzaamheden",
      field: "arbeidsVoorwaarden.datumAanvangWerkzaamheden",
      filter: true,
      valueFormatter: dateFormatter
    },
    {
      headerName: "Datum einde werkzaamheden",
      field: "arbeidsVoorwaarden.datumEindeWerkzaamheden",
      filter: true,
      valueFormatter: dateFormatter
    },
    {
      headerName: "Detail aangevraagd", field: "fetchedDetail", cellRenderer: 'btnCellRendererFetched'
    },
    {
      headerName: "", cellRenderer: 'btnCellRendererMoreDetail',
      cellRendererParams: (params: any) => {
        return {
          urlPath: "/vacature/" + this.vraagId + "/" + params.data.vumID,
          buttonText: "Meer detail",
          onClick: () => this.vacaturePassService.setVacature(params.data)
        }
      }
    }
  ];

  constructor(private route: ActivatedRoute,
              private location: Location,
              private vacatureService: VacatureBemiddelaarService,
              private vacaturePassService: VacaturePassService) {

  }

  ngOnInit(): void {
    this.vraagId = this.route.snapshot.paramMap.get('vraagId');
    this.getVacature();

    this.gridOptions = <GridOptions>{
      defaultColDef: {
        editable: false,
        sortable: true
      },
      pagination: true,
      paginationAutoPageSize: true,
      frameworkComponents: {
        btnCellRendererMoreDetail: BtnCellRendererMoreDetail,
        btnCellRendererFetched: ButtonCellRendererFetched
      },
      getRowNodeId: (data: MPVacatureMatch) => {
        return data.vumID;
      },
      onGridReady: (event) => {
        this.gridApi = event.api;
        event.api.sizeColumnsToFit()
      }
    }
  }

  private getVacature(): void {
    if (this.vraagId) {
      this.aanvraagVacature$ = this.vacatureService.getVraagId(this.vraagId);
    }
  }

  goBack(): void {
    this.location.back();
  }

  refresh(): void {
    this.aanvraagVacature$.subscribe(aanvraagVacature =>
      this.gridApi.setRowData(aanvraagVacature.vacatures)
    );
  }

}
