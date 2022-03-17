import {Component, OnInit} from '@angular/core';
import {EMPTY, Observable} from "rxjs";
import {ColDef, GridApi, GridOptions} from "ag-grid-community";
import {ActivatedRoute} from "@angular/router";
import {Location} from "@angular/common";
import {BtnCellRendererMoreDetail} from "../../buttons/button-cell-renderer-more-details/button-cell-renderer-more-detail";
import {AanvraagWerkzoekende} from "../../entities/werkzoekende/aanvraagWerkzoekende";
import {WerkzoekendePassService} from "../../services/werkzoekende-pass.service";
import {MPWerkzoekendeMatchBemiddelaar} from "../../entities/werkzoekende/mPWerkzoekendeMatchBemiddelaar";
import {WerkzoekendeBemiddelaarService} from "../../services/werkzoekende-bemiddelaar.service";
import {
  ButtonCellRendererFetched
} from "../../buttons/button-cell-renderer-fetched/button-cell-renderer-fetched.component";

@Component({
  selector: 'app-werkzoekende-vraagid-detail',
  templateUrl: './werkzoekende-vraagid-detail.component.html',
  styleUrls: ['./werkzoekende-vraagid-detail.component.css']
})
export class WerkzoekendeVraagidDetailComponent implements OnInit {

  aanvraagWerkzoekende$: Observable<AanvraagWerkzoekende> = EMPTY;
  vraagId?: string | null;

  gridApi!: GridApi;
  gridOptions!: GridOptions;
  columnDefs: ColDef[] = [
    {headerName: "Vum ID", field: 'vumID', filter: true},
    {headerName: "Aantal werkuren per week minimaal", field: 'werktijden.aantalWerkurenPerWeekMinimaal', filter: true},
    {headerName: "Aantal werkuren per week maximaal", field: "werktijden.aantalWerkurenPerWeekMaximaal", filter: true},
    {
      headerName: "Detail aangevraagd", field: "fetchedDetail", cellRenderer: 'btnCellRendererFetched'
    },
    {
      headerName: "", cellRenderer: 'btnCellRendererMoreDetail',
      cellRendererParams: (params: any) => {
        return {
          urlPath: "/werkzoekende/" + this.vraagId + "/" + params.data.vumID,
          buttonText: "Meer detail",
          onClick: () => this.werkzoekendePassService.setWerkzoekende(params.data)
        }
      }
    }
  ];

  constructor(private route: ActivatedRoute,
              private location: Location,
              private werkzoekendeService: WerkzoekendeBemiddelaarService,
              private werkzoekendePassService: WerkzoekendePassService) {

  }

  ngOnInit(): void {
    this.vraagId = this.route.snapshot.paramMap.get('vraagId');
    this.getWerkzoekende();

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
      getRowNodeId: (data: MPWerkzoekendeMatchBemiddelaar) => {
        return data.vumID;
      },
      onGridReady: (event) => {
        this.gridApi = event.api;
        event.api.sizeColumnsToFit()
      }
    }
  }

  private getWerkzoekende(): void {
    if (this.vraagId) {
      this.aanvraagWerkzoekende$ = this.werkzoekendeService.getVraagId(this.vraagId);
    }
  }

  goBack(): void {
    this.location.back();
  }

  refresh(): void {
    this.aanvraagWerkzoekende$.subscribe(aanvraagWerkzoekende =>
      this.gridApi.setRowData(aanvraagWerkzoekende.werkzoekenden)
    );
  }

}
