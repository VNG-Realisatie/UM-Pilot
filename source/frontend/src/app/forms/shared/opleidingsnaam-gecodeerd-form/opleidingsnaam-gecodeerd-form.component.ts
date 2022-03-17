import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AbstractBaseFormComponent} from "../../abstract-base-form/abstract-base-form.component";

@Component({
  selector: 'app-opleidingsnaam-gecodeerd-form',
  templateUrl: './opleidingsnaam-gecodeerd-form.component.html',
  styleUrls: ['./opleidingsnaam-gecodeerd-form.component.css']
})
export class OpleidingsnaamGecodeerdFormComponent extends AbstractBaseFormComponent implements OnInit {

  @Output()
  formReady = new EventEmitter<FormGroup>();

  form = this.fb.group({
    codeOpleidingsnaam: ['', [Validators.min(0), Validators.max(9999999999), Validators.required]],
    omschrijvingOpleidingsnaam: ['', [Validators.maxLength(120), Validators.required]]
  })

  constructor(private fb: FormBuilder) {
    super()
  }

  ngOnInit(): void {
    this.formReady.emit(this.form);
  }

}
