import {ComponentFixture, TestBed} from '@angular/core/testing';

import {OpleidingsnaamGecodeerdMatchComponent} from './opleidingsnaam-gecodeerd-match.component';

describe('OpleidingsnaamGecodeerdMatchComponent', () => {
  let component: OpleidingsnaamGecodeerdMatchComponent;
  let fixture: ComponentFixture<OpleidingsnaamGecodeerdMatchComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [OpleidingsnaamGecodeerdMatchComponent]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(OpleidingsnaamGecodeerdMatchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
