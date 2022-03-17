import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AbstractBaseFormComponent} from "../../abstract-base-form/abstract-base-form.component";

@Component({
  selector: 'app-opleidingsnaam-ongecodeerd-form',
  templateUrl: './opleidingsnaam-ongecodeerd-form.component.html',
  styleUrls: ['./opleidingsnaam-ongecodeerd-form.component.css']
})
export class OpleidingsnaamOngecodeerdFormComponent extends AbstractBaseFormComponent implements OnInit {

  @Output()
  formReady = new EventEmitter<FormGroup>();

  form = this.fb.group({
    naamOpleidingOngecodeerd: ['', [Validators.maxLength(120), Validators.required]]
  })

  constructor(private fb: FormBuilder) {
    super();
  }

  ngOnInit(): void {
    this.formReady.emit(this.form);

  }

}
