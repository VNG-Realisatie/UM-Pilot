import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {codeWerkEnDenkniveauMinimaalValues, indicatieTwoValues} from "../../../forms-util/formcontrol-value-enums"
import {AbstractBaseFormComponent} from "../../abstract-base-form/abstract-base-form.component";
import {Subscription} from "rxjs";
import {dateFormatterNgbDate} from "../../../util/dateformatter";
import {MPVacature} from "../../../entities/vacature/mPVacature";

@Component({
  selector: 'app-basic-information-vacature',
  templateUrl: './basic-information-vacature.component.html',
  styleUrls: ['./basic-information-vacature.component.css']
})
export class BasicInformationVacatureComponent extends AbstractBaseFormComponent implements OnInit {

  @Output()
  formReady = new EventEmitter<FormGroup>();

  @Output()
  valueChangeVacature = new EventEmitter<Partial<MPVacature>>();

  @Output()
  valueChangesPostcodeAndStraal = new EventEmitter<Partial<{ postcode: string, straal: number, aanvraagKenmerk: string }>>();

  codeWerkEnDenkniveauMinimaalValues = codeWerkEnDenkniveauMinimaalValues;
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
      codeWerkEnDenkniveauMinimaal: ['', []],
      indicatieLdrRegistratie: ['', []],
      sluitingsDatumVacature: [null, []],
    });

    this.subscription.add(
      this.form.valueChanges.subscribe((value) => {
        this.valueChangeVacature.emit({
          codeWerkEnDenkniveauMinimaal: value.codeWerkEnDenkniveauMinimaal,
          indicatieLdrRegistratie: value.indicatieLdrRegistratie,
          sluitingsDatumVacature: dateFormatterNgbDate(value.sluitingsDatumVacature)
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
