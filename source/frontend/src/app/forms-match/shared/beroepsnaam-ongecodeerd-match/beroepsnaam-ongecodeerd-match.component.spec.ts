import {ComponentFixture, TestBed} from '@angular/core/testing';

import {BeroepsnaamOngecodeerdMatchComponent} from './beroepsnaam-ongecodeerd-match.component';

describe('BeroepsnaamOngecodeerdMatchComponent', () => {
  let component: BeroepsnaamOngecodeerdMatchComponent;
  let fixture: ComponentFixture<BeroepsnaamOngecodeerdMatchComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [BeroepsnaamOngecodeerdMatchComponent]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BeroepsnaamOngecodeerdMatchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
