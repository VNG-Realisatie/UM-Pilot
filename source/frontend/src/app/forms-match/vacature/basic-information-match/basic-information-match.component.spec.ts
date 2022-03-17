import {ComponentFixture, TestBed} from '@angular/core/testing';

import {BasicInformationMatchComponent} from './basic-information-match.component';

describe('BasicInformationMatchComponent', () => {
  let component: BasicInformationMatchComponent;
  let fixture: ComponentFixture<BasicInformationMatchComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [BasicInformationMatchComponent]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BasicInformationMatchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
