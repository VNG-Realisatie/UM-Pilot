import {ComponentFixture, TestBed} from '@angular/core/testing';

import {BasicInformationWerkzoekendeMatchComponent} from './basic-information-werkzoekende-match.component';

describe('BasicInformationWerkzoekendeMatchComponent', () => {
  let component: BasicInformationWerkzoekendeMatchComponent;
  let fixture: ComponentFixture<BasicInformationWerkzoekendeMatchComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [BasicInformationWerkzoekendeMatchComponent]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BasicInformationWerkzoekendeMatchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
