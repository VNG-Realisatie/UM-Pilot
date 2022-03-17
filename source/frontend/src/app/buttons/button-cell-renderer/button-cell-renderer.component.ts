import {Component, OnDestroy} from "@angular/core";
import {ICellRendererAngularComp} from "ag-grid-angular";
import {ICellRendererParams} from "ag-grid-community";

@Component({
  selector: 'btn-cell-renderer',
  templateUrl: './button-cell-renderer.html',
})
export class BtnCellRenderer implements ICellRendererAngularComp, OnDestroy {
  private params: any;
  urlPath?: string;
  buttonText?: string;

  agInit(params: any): void {
    this.params = params;
    this.urlPath = params.urlPath;
    this.buttonText = params.buttonText;
  }

  ngOnDestroy() {
    // no need to remove the button click handler
    // https://stackoverflow.com/questions/49083993/does-angular-automatically-remove-template-event-listeners
  }

  refresh(params: ICellRendererParams): boolean {
    throw new Error("Method not implemented.");
  }

}
