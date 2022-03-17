import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {FormArray, FormBuilder, FormGroup} from "@angular/forms";
import {Subscription} from "rxjs";
import {AbstractBaseFormComponent} from "../../abstract-base-form/abstract-base-form.component";
import {MPWerkzoekende} from "../../../entities/werkzoekende/mPWerkzoekende";
import {Beroepsnaam} from "../../../entities/shared/beroepsnaam";

@Component({
  selector: 'app-bemiddelingsberoep-werkzoekende-form',
  templateUrl: './bemiddelingsberoep-werkzoekende-form.component.html',
  styleUrls: ['./bemiddelingsberoep-werkzoekende-form.component.css']
})
export class BemiddelingsberoepWerkzoekendeFormComponent extends AbstractBaseFormComponent implements OnInit {


  @Output()
  formReady = new EventEmitter<FormArray>();

  @Output()
  valueChangeWerkzoekende = new EventEmitter<Partial<MPWerkzoekende>>();

  form: FormGroup = this.fb.group({});

  private subscription = new Subscription();


  constructor(private fb: FormBuilder) {
    super();
  }

  ngOnInit(): void {
    this.form = this.fb.group({
      bemiddelingsberoep: this.fb.array([])
    })

    this.subscription.add(
      this.form.controls.bemiddelingsberoep.valueChanges.subscribe((value) => {
        const mappedValue = value.map((x: { beroepsnaam: Beroepsnaam }) => {
            return x.beroepsnaam
          }
        );
        this.valueChangeWerkzoekende.emit({
          bemiddelingsberoep: mappedValue
        });
      })
    );

    this.formReady.emit(this.t);
  }

  addBeroep() {
    this.t.push(this.fb.group({
      beroepsnaam: this.fb.group({})
    }));
  }

  removeBeroep(i: number) {
    this.t.removeAt(i);
  }

  get t() {
    return this.f.bemiddelingsberoep as FormArray;
  }

  updateBeroep(beroepControl: FormGroup, group: FormGroup) {
    beroepControl.setControl('beroepsnaam', group);
  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }

}
