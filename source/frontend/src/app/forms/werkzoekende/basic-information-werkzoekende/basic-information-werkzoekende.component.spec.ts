import {ComponentFixture, TestBed} from '@angular/core/testing';

import {BasicInformationWerkzoekendeComponent} from './basic-information-werkzoekende.component';

describe('BasicInformationWerkzoekendeComponent', () => {
  let component: BasicInformationWerkzoekendeComponent;
  let fixture: ComponentFixture<BasicInformationWerkzoekendeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [BasicInformationWerkzoekendeComponent]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BasicInformationWerkzoekendeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
