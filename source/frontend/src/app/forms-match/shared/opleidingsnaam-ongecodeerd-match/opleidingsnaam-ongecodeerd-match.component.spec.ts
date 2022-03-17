import {ComponentFixture, TestBed} from '@angular/core/testing';

import {OpleidingsnaamOngecodeerdMatchComponent} from './opleidingsnaam-ongecodeerd-match.component';

describe('OpleidingsnaamOngecodeerdMatchComponent', () => {
  let component: OpleidingsnaamOngecodeerdMatchComponent;
  let fixture: ComponentFixture<OpleidingsnaamOngecodeerdMatchComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [OpleidingsnaamOngecodeerdMatchComponent]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(OpleidingsnaamOngecodeerdMatchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
