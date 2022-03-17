import {ComponentFixture, TestBed} from '@angular/core/testing';

import {BasicInformationMatchDetailedComponent} from './basic-information-match-detailed.component';

describe('BasicInformationMatchDetailedComponent', () => {
  let component: BasicInformationMatchDetailedComponent;
  let fixture: ComponentFixture<BasicInformationMatchDetailedComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [BasicInformationMatchDetailedComponent]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BasicInformationMatchDetailedComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
