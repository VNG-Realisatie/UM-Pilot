import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AbstractBaseFormComponent} from "../../abstract-base-form/abstract-base-form.component";
import {Subscription} from "rxjs";
import {MPVacature} from "../../../entities/vacature/mPVacature";

@Component({
  selector: 'app-beroepsnaam-ongecodeerd-form',
  templateUrl: './beroepsnaam-ongecodeerd-form.component.html',
  styleUrls: ['./beroepsnaam-ongecodeerd-form.component.css']
})
export class BeroepsnaamOngecodeerdFormComponent extends AbstractBaseFormComponent implements OnInit {

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
      naamBeroepOngecodeerd: ['', [Validators.maxLength(120)]]
    })

    this.subscription.add(
      this.form.valueChanges.subscribe((value) => {
        this.valueChange.emit({
          beroep: {
            naamBeroepOngecodeerd: value.naamBeroepOngecodeerd
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
