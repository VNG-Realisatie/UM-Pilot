import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {FormArray, FormBuilder, FormGroup} from "@angular/forms";
import {MPWerkzoekende} from "../../../entities/werkzoekende/mPWerkzoekende";
import {Subscription} from "rxjs";
import {AbstractBaseFormComponent} from "../../abstract-base-form/abstract-base-form.component";
import {codeWerkEnDenkniveauMinimaalValues} from "../../../forms-util/formcontrol-value-enums";
import {MPArbeidsmarktkwalificatie} from "../../../entities/werkzoekende/mPArbeidsmarktkwalificatie";

@Component({
  selector: 'app-arbeidsmarktkwalificatie-form',
  templateUrl: './arbeidsmarktkwalificatie-form.component.html',
  styleUrls: ['./arbeidsmarktkwalificatie-form.component.css']
})
export class ArbeidsmarktkwalificatieFormComponent extends AbstractBaseFormComponent implements OnInit {

  @Output()
  formReady = new EventEmitter<FormGroup>();

  @Output()
  valueChangeWerkzoekende = new EventEmitter<Partial<MPWerkzoekende>>(true);

  form: FormGroup = this.fb.group({});

  arbeidsmarktkwalificatie: MPArbeidsmarktkwalificatie = {};

  codeWerkEnDenkniveauValues = codeWerkEnDenkniveauMinimaalValues


  private subscription = new Subscription();

  constructor(private fb: FormBuilder) {
    super();
  }

  ngOnInit(): void {
    this.form = this.fb.group({
      codeWerkEnDenkniveauWerkzoekende: ['', []]
    })

    this.subscription.add(
      this.form.controls.codeWerkEnDenkniveauWerkzoekende.valueChanges.subscribe((value) => {
        //trigger emit even when only codewerkendenkniveau is filled
        this.onValueChange({})
      })
    );

    this.formReady.emit(this.form);
  }

  addChildFormArray(name: string, array: FormArray) {
    this.form.addControl(name, array);
  }

  onValueChange(changes: Partial<MPArbeidsmarktkwalificatie>) {
    this.arbeidsmarktkwalificatie = {
      ...this.arbeidsmarktkwalificatie,
      ...changes,
      codeWerkEnDenkniveauWerkzoekende: this.form.controls.codeWerkEnDenkniveauWerkzoekende.value
    };
    this.valueChangeWerkzoekende.emit({arbeidsmarktkwalificatie: this.arbeidsmarktkwalificatie})
  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }


}
