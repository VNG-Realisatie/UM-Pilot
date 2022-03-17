import {ComponentFixture, TestBed} from '@angular/core/testing';

import {OpleidingMatchComponent} from './opleiding-match.component';

describe('OpleidingMatchComponent', () => {
  let component: OpleidingMatchComponent;
  let fixture: ComponentFixture<OpleidingMatchComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [OpleidingMatchComponent]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(OpleidingMatchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
