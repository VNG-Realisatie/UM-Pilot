import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {FormGroup} from "@angular/forms";

@Component({
  selector: 'app-opleidingsnaam-form',
  templateUrl: './opleidingsnaam-form.component.html',
  styleUrls: ['./opleidingsnaam-form.component.css']
})
export class OpleidingsnaamFormComponent implements OnInit {

  @Output()
  formReady = new EventEmitter<FormGroup>(true);

  codedValue: number = 0;

  constructor() {
  }

  ngOnInit(): void {
  }

  addForm(group: FormGroup) {
    this.formReady.emit(group);
  }

}
