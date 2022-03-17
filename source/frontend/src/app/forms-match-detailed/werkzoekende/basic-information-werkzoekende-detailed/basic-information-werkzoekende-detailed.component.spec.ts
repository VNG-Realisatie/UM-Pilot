import {ComponentFixture, TestBed} from '@angular/core/testing';

import {BasicInformationWerkzoekendeDetailedComponent} from './basic-information-werkzoekende-detailed.component';

describe('BasicInformationWerkzoekendeDetailedComponent', () => {
  let component: BasicInformationWerkzoekendeDetailedComponent;
  let fixture: ComponentFixture<BasicInformationWerkzoekendeDetailedComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [BasicInformationWerkzoekendeDetailedComponent]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BasicInformationWerkzoekendeDetailedComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
