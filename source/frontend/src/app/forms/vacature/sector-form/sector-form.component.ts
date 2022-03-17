import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AbstractBaseFormComponent} from "../../abstract-base-form/abstract-base-form.component";
import {Subscription} from "rxjs";
import {MPVacature} from "../../../entities/vacature/mPVacature";

@Component({
  selector: 'app-sector-form',
  templateUrl: './sector-form.component.html',
  styleUrls: ['./sector-form.component.css']
})
export class SectorFormComponent extends AbstractBaseFormComponent implements OnInit {

  @Output()
  formReady = new EventEmitter<FormGroup>();

  @Output()
  valueChangeVacature = new EventEmitter<Partial<MPVacature>>();

  form: FormGroup = this.fb.group({});

  private subscription = new Subscription();

  constructor(private fb: FormBuilder) {
    super();
  }

  ngOnInit(): void {
    this.form = this.fb.group({
      codeSbi: ['', [Validators.min(0), Validators.max(99999)]]
    })

    this.subscription.add(
      this.form.valueChanges.subscribe((value) => {
        const entity = {
          sector: {
            codeSbi: value.codeSbi
          }
        };
        this.valueChangeVacature.emit(entity);
      })
    );

    this.formReady.emit(this.form);
  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }

}
