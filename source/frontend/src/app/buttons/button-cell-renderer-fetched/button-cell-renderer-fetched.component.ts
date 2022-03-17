import {Component, OnDestroy, OnInit} from '@angular/core';
import {ICellRendererAngularComp} from "ag-grid-angular";
import {ICellRendererParams} from "ag-grid-community";

@Component({
  selector: 'app-button-cell-renderer-fetched',
  templateUrl: './button-cell-renderer-fetched.component.html',
  styleUrls: ['./button-cell-renderer-fetched.component.css']
})
export class ButtonCellRendererFetched implements ICellRendererAngularComp, OnDestroy{

  fetched : boolean = false;

  constructor() { }

  ngOnInit(): void {
  }

  agInit(params: ICellRendererParams): void {
    this.fetched = params.data.fetchedDetail;
  }

  ngOnDestroy(): void {
  }

  refresh(params: ICellRendererParams): boolean {
    return false;
  }

}
