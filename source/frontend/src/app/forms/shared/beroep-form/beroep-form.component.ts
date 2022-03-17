import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {FormGroup} from "@angular/forms";
import {MPVacature} from "../../../entities/vacature/mPVacature";
import {MPWerkervaring} from "../../../entities/werkzoekende/mPWerkervaring";

@Component({
  selector: 'app-beroep-form',
  templateUrl: './beroep-form.component.html',
  styleUrls: ['./beroep-form.component.css']
})
export class BeroepFormComponent implements OnInit {

  @Output()
  formReady = new EventEmitter<FormGroup>(true);

  @Output()
  valueChangeVacature = new EventEmitter<Partial<MPVacature>>();

  @Output()
  valueChangeWerkzoekende = new EventEmitter<Partial<MPWerkervaring>>();

  codedValue: number = 0;

  constructor() {
  }

  ngOnInit(): void {
  }

  addForm(group: FormGroup) {
    this.formReady.emit(group);
  }

  onValueChangeVacature(changes: Partial<MPVacature>) {
    this.valueChangeVacature.emit(changes);
  }

  onValueChangeWerkzoekende(changes: Partial<MPWerkervaring>) {
    this.valueChangeWerkzoekende.emit(changes);
  }


  onSelectedChange(value: number) {
    if (value === 0) {
      this.formReady.emit(new FormGroup({}));
      const entity = {
        beroep: undefined
      };
      this.valueChangeVacature.emit(entity);
      this.valueChangeWerkzoekende.emit(entity);
    }
  }
}
