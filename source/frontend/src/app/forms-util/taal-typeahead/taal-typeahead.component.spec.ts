import {ComponentFixture, TestBed} from '@angular/core/testing';

import {TaalTypeaheadComponent} from './taal-typeahead.component';

describe('TaalTypeaheadComponent', () => {
  let component: TaalTypeaheadComponent;
  let fixture: ComponentFixture<TaalTypeaheadComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [TaalTypeaheadComponent]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TaalTypeaheadComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
