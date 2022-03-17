import {ComponentFixture, TestBed} from '@angular/core/testing';

import {BasicInformationVacatureComponent} from './basic-information-vacature.component';

describe('BasicInformationComponent', () => {
  let component: BasicInformationVacatureComponent;
  let fixture: ComponentFixture<BasicInformationVacatureComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [BasicInformationVacatureComponent]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BasicInformationVacatureComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
