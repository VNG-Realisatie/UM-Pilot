import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {FormArray, FormBuilder, FormGroup, Validators} from "@angular/forms";
import {MPArbeidsmarktkwalificatie} from "../../../entities/werkzoekende/mPArbeidsmarktkwalificatie";
import {Subscription} from "rxjs";
import {dateFormatterNgbDate} from "../../../util/dateformatter";
import {AbstractBaseFormComponent} from "../../abstract-base-form/abstract-base-form.component";
import {MPWerkervaring} from "../../../entities/werkzoekende/mPWerkervaring";

@Component({
  selector: 'app-werkervaring-werkzoekende-form',
  templateUrl: './werkervaring-werkzoekende-form.component.html',
  styleUrls: ['./werkervaring-werkzoekende-form.component.css']
})
export class WerkervaringWerkzoekendeFormComponent extends AbstractBaseFormComponent implements OnInit {

  @Output()
  formReady = new EventEmitter<FormArray>();

  @Output()
  valueChangeWerkzoekende = new EventEmitter<Partial<MPArbeidsmarktkwalificatie>>();

  form: FormGroup = this.fb.group({});

  private subscription = new Subscription();


  constructor(private fb: FormBuilder) {
    super();
  }

  ngOnInit(): void {
    this.form = this.fb.group({
      werkervaring: this.fb.array([])
    })

    this.subscription.add(
      this.form.controls.werkervaring.valueChanges.subscribe((value) => {

        const mappedValues = value.map((x: MPWerkervaring) => {
          return {
            datumAanvangWerkzaamheden: dateFormatterNgbDate(x.datumAanvangWerkzaamheden),
            datumEindeWerkzaamheden: dateFormatterNgbDate(x.datumEindeWerkzaamheden),
            naamOrganisatie: x.naamOrganisatie,
            beroep: x.beroep
          }
        });

        this.valueChangeWerkzoekende.emit({
          werkervaring: mappedValues
        });
      })
    );

    this.formReady.emit(this.t);
  }

  addWerkervaring() {
    this.t.push(this.fb.group({
      datumAanvangWerkzaamheden: [null, []],
      datumEindeWerkzaamheden: [null, []],
      naamOrganisatie: ['', [Validators.minLength(0), Validators.maxLength(70)]],
      beroep: this.fb.group({})
    }));
  }

  removeWerkervaring(i: number) {
    this.t.removeAt(i);
  }

  updateWerkervaring(werkervaringControl: FormGroup, group: FormGroup) {
    werkervaringControl.setControl('beroep', group);
  }

  get t() {
    return this.f.werkervaring as FormArray;
  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }

}
