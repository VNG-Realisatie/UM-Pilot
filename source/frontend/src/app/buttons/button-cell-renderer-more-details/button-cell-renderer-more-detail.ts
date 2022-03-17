import {Component, OnDestroy} from "@angular/core";
import {ICellRendererAngularComp} from "ag-grid-angular";
import {ICellRendererParams} from "ag-grid-community";
import {MPVacatureMatch} from "../../entities/vacature/mPVacatureMatch";

@Component({
  selector: 'btn-cell-renderer',
  templateUrl: './button-cell-renderer-more-detail.html',
})
export class BtnCellRendererMoreDetail implements ICellRendererAngularComp, OnDestroy {
  private params: any;
  urlPath?: string;
  buttonText?: string;
  data?: MPVacatureMatch

  constructor() {
  }

  agInit(params: any): void {
    this.params = params;
    this.urlPath = params.urlPath
    this.buttonText = params.buttonText;
    this.data = params.data;


  }

  ngOnDestroy() {
    // no need to remove the button click handler
    // https://stackoverflow.com/questions/49083993/does-angular-automatically-remove-template-event-listeners
  }

  refresh(params: ICellRendererParams): boolean {
    return false;
  }

  onClick() {
    this.params.onClick();
  }
}
