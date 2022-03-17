import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {indicatieTwoValues} from "../../../forms-util/formcontrol-value-enums";
import {Subscription} from "rxjs";
import {AbstractBaseFormComponent} from "../../abstract-base-form/abstract-base-form.component";
import {MPWerkzoekende} from "../../../entities/werkzoekende/mPWerkzoekende";

@Component({
  selector: 'app-basic-information-werkzoekende',
  templateUrl: './basic-information-werkzoekende.component.html',
  styleUrls: ['./basic-information-werkzoekende.component.css']
})
export class BasicInformationWerkzoekendeComponent extends AbstractBaseFormComponent implements OnInit {

  @Output()
  formReady = new EventEmitter<FormGroup>();

  @Output()
  valueChangeWerkzoekende = new EventEmitter<Partial<MPWerkzoekende>>();

  @Output()
  valueChangesPostcodeAndStraal = new EventEmitter<Partial<{ postcode: string, straal: number, aanvraagKenmerk: string }>>();

  indicatieValues = indicatieTwoValues;

  form: FormGroup = this.fb.group({});

  private subscription = new Subscription();

  constructor(private fb: FormBuilder) {
    super();
  }

  ngOnInit(): void {
    this.form = this.fb.group({
      postcode: ['', [Validators.required, Validators.pattern(/^[1-9][0-9]{3}(?!sa|sd|ss)[a-z]{2}$/i)]],
      straal: ['', [Validators.required, Validators.max(999), Validators.min(0)]],
      aanvraagKenmerk: ['', [Validators.required]],
      indicatieLdrRegistratie: ['', []],
      indicatieBeschikbaarheidContactgegevens: ['', []],
    });

    this.subscription.add(
      this.form.valueChanges.subscribe((value) => {
        this.valueChangeWerkzoekende.emit({
          indicatieLdrRegistratie: value.indicatieLdrRegistratie,
          indicatieBeschikbaarheidContactgegevens: value.indicatieBeschikbaarheidContactgegevens
        })

        this.valueChangesPostcodeAndStraal.emit({
          postcode: value.postcode,
          straal: value.straal,
          aanvraagKenmerk: value.aanvraagKenmerk
        })
      })
    );

    this.formReady.emit(this.form);
  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }

}
