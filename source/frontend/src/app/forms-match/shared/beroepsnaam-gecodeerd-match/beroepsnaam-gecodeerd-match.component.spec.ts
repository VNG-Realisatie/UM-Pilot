import {ComponentFixture, TestBed} from '@angular/core/testing';

import {BeroepsnaamGecodeerdMatchComponent} from './beroepsnaam-gecodeerd-match.component';

describe('BeroepsnaamGecodeerdMatchComponent', () => {
  let component: BeroepsnaamGecodeerdMatchComponent;
  let fixture: ComponentFixture<BeroepsnaamGecodeerdMatchComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [BeroepsnaamGecodeerdMatchComponent]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BeroepsnaamGecodeerdMatchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
