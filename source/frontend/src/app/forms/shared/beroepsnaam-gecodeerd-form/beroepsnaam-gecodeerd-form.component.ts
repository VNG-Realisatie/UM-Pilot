import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AbstractBaseFormComponent} from "../../abstract-base-form/abstract-base-form.component";
import {Subscription} from "rxjs";
import {MPVacature} from "../../../entities/vacature/mPVacature";

@Component({
  selector: 'app-beroepsnaam-gecodeerd-form',
  templateUrl: './beroepsnaam-gecodeerd-form.component.html',
  styleUrls: ['./beroepsnaam-gecodeerd-form.component.css']
})
export class BeroepsnaamGecodeerdFormComponent extends AbstractBaseFormComponent implements OnInit {

  @Output()
  formReady = new EventEmitter<FormGroup>();

  @Output()
  valueChange = new EventEmitter<Partial<MPVacature>>();

  private subscription = new Subscription();

  form: FormGroup = this.fb.group({})

  constructor(private fb: FormBuilder) {
    super();
  }

  ngOnInit(): void {

    this.form = this.fb.group({
      codeBeroepsnaam: ['', [Validators.maxLength(10), Validators.required]],
      omschrijvingBeroepsnaam: ['', [Validators.maxLength(120), Validators.required]]
    })

    this.subscription.add(
      this.form.valueChanges.subscribe((value) => {
        this.valueChange.emit({
          beroep: {
            codeBeroepsnaam: value.codeBeroepsnaam,
            omschrijvingBeroepsnaam: value.omschrijvingBeroepsnaam,
          }
        });
      })
    );
    this.formReady.emit(this.form);
  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }


}
