# Material 3 Expressive & Jetpack Compose: The Complete Specification & AI Knowledge Base

> **Document Version:** 1.0.0  
> **Schema Type:** Universal AI-Ingestible Knowledge Graph & Implementation Standard  
> **Target Framework:** Android Jetpack Compose 1.7+ / Compose Material 3 1.3+ & 1.4+  
> **Primary Sources:**
> - [Material 3 Foundations](https://m3.material.io/foundations)
> - [Material 3 Styles](https://m3.material.io/styles)
> - [Material 3 Components](https://m3.material.io/components)
> - [Building with Material 3 Expressive](https://m3.material.io/blog/building-with-m3-expressive)
> - [Android Jetpack Compose Documentation](https://developer.android.com/develop/ui/compose/documentation)

---

## 1. Executive Summary & Design Philosophy

### 1.1 What is Material 3 Expressive?
Material 3 Expressive (M3 Expressive) is the major visual and interactive evolution of Google's Material Design 3 system. While baseline Material 3 established tonal color systems and dynamic theming (Material You), M3 Expressive bridges the gap between functional utility and emotional resonance. It emphasizes:
1. **Bold Visual Hierarchy:** Moving away from monotonous gray grids toward expressive tonal layering.
2. **Tactile Geometry:** Replacing modest 4dp-8dp corner radiuses with generous, organic curves up to 32dp and pill shapes.
3. **Assertive Color Contrasts:** Elevating the Tertiary color role from an occasional accent into a primary personality driver.
4. **Physics-Driven Motion:** Utilizing spring-based physics schemes and dynamic shape morphing rather than mechanical linear tweens.
5. **Surface Container Architecture:** Completely eliminating arbitrary drop shadows in favor of 5 calibrated container levels plus dim/bright surfaces.

### 1.2 Comparison Matrix: M2 vs. Baseline M3 vs. M3 Expressive

| Attribute | Material 2 (M2) | Baseline Material 3 (M3) | Material 3 Expressive |
| :--- | :--- | :--- | :--- |
| **Elevation** | Drop shadows (black blur, `dp` elevation) | Tonal color tint on surface | **Surface Container System** (Lowest, Low, Default, High, Highest) |
| **Corner Curvature** | Fixed (4dp-8dp) | Moderate (4dp-16dp) | **Expanded Scales** (4dp, 8dp, 16dp, 24dp, 32dp, Pill/Circle) |
| **Color Roles** | Primary & Secondary only | Primary, Secondary, Tertiary (subtle) | **Dynamic Tonal Contrast** (Tertiary elevated as expressive focal anchor) |
| **Typography Scale** | 6 headline variants | 15 scale tokens (standard weight) | **15 Expressive Tokens** (Higher contrast Display/Headline vs Body) |
| **Component Personality** | Boxy, functional | Softened corners | **Playful, tactile, morphing, split buttons, large FABs** |
| **Motion Model** | Easing curves (fast-out-slow-in) | Standard bezier easing | **Spring physics, shape-morphing, interactive stretch** |

---

## 2. The Color System & Token Specification

### 2.1 Tonal Palette Architecture
The M3 color system generates 13 algorithmic tonal values (from Tone 0 to Tone 100) for each key color:
- **Tone 100:** Pure White `#FFFFFF`
- **Tone 95:** Very subtle light tint
- **Tone 90:** Standard light container background
- **Tone 80:** Light mode dominant text / Dark mode primary elements
- **Tone 40:** Light mode primary brand color
- **Tone 30:** Dark container background
- **Tone 20:** Dark mode high-emphasis elements
- **Tone 10:** Deep dark accents
- **Tone 0:** Pure Black `#000000`

### 2.2 Core Color Roles

```
[Primary] ───────► Dominant brand color. Used for key actions (Filled Buttons, active tabs, selected states).
[OnPrimary] ─────► High-contrast text/icon drawn ON TOP of Primary. Guaranteed 4.5:1 WCAG contrast.
[PrimaryContainer] ► Tonal container with lower emphasis than Primary. Used for highlighted cards, badges.
[OnPrimaryContainer] ► High-contrast text/icon drawn ON TOP of PrimaryContainer.

[Secondary] ─────► Supportive brand color. Less prominent than Primary.
[OnSecondary] ───► High-contrast text/icon drawn ON TOP of Secondary.
[SecondaryContainer] ► Used for tonal buttons, selected chips, and navigation indicator pills.
[OnSecondaryContainer] ► High-contrast text/icon drawn ON TOP of SecondaryContainer.

[Tertiary] ──────► THE EXPRESSIVE ANCHOR. Deliberately chosen to contrast against Primary and Secondary.
                   Used for celebratory moments, notification dots, playful tags, and large FABs.
[OnTertiary] ────► High-contrast text/icon drawn ON TOP of Tertiary.
[TertiaryContainer] ► Used for expressive card accents, banners, and callouts.
[OnTertiaryContainer] ► High-contrast text/icon drawn ON TOP of TertiaryContainer.

[Error] ─────────► Expresses critical states (validation failures, connection drops).
[OnError] ───────► Contrast text on Error.
[ErrorContainer] ─► Background for error alerts, snackbars, and form field alerts.
[OnErrorContainer] ► Text/icon on ErrorContainer.
```

### 2.3 The Surface Container Hierarchy (Drop Shadow Replacement)
In M3 Expressive, physical drop shadows are deprecated in favor of surface tonal tiers. This ensures dark mode interfaces do not lose visual hierarchy.

```
┌────────────────────────────────────────────────────────┐
│  Surface Container Highest (e.g. Floating Dialogs)     │
│  ┌──────────────────────────────────────────────────┐  │
│  │  Surface Container High (e.g. Filter Sheets)     │  │
│  │  ┌────────────────────────────────────────────┐  │  │
│  │  │  Surface Container (Default: Cards)        │  │  │
│  │  │  ┌──────────────────────────────────────┐  │  │  │
│  │  │  │  Surface Container Low (Lists)       │  │  │  │
│  │  │  │  ┌────────────────────────────────┐  │  │  │  │
│  │  │  │  │  Surface Container Lowest      │  │  │  │  │
│  │  │  │  │  (Absolute Canvas Background)  │  │  │  │  │
│  │  │  │  └────────────────────────────────┘  │  │  │  │
│  │  │  └──────────────────────────────────────┘  │  │  │
│  │  └────────────────────────────────────────────┘  │  │
│  └──────────────────────────────────────────────────┘  │
└────────────────────────────────────────────────────────┘
```

| Token Name | Light Mode Value (Reference) | Dark Mode Value (Reference) | Architectural Purpose |
| :--- | :--- | :--- | :--- |
| `surfaceContainerLowest` | `#FFFFFF` | `#0E0E13` | Deepest canvas background, list groupings |
| `surfaceContainerLow` | `#F5F2FA` | `#1B1B21` | Inset cards, search bar backdrops |
| `surfaceContainer` | `#EFEDF4` | `#1F1F25` | Default standard cards, modal containers |
| `surfaceContainerHigh` | `#EAE7EF` | `#2A292F` | Interactive selection cards, elevated menus |
| `surfaceContainerHighest`| `#E4E1E9` | `#35343A` | Modals, high-emphasis dialogs, floating toolbars |
| `surfaceDim` | `#DBD9E0` | `#131318` | Receding background under sheets |
| `surfaceBright` | `#FBF8FF` | `#39393F` | High-luminance accent surface |

---

## 3. The Shape System & Geometry

### 3.1 Corner Radius Scale
Material 3 Expressive expands the corner scale to provide fluid, tactile touch boundaries:

| Scale Token | Radius Value | Applied Components |
| :--- | :--- | :--- |
| **None** | `0.dp` | Full-screen surfaces, edge-to-edge banners |
| **Extra Small** | `4.dp` | Micro tags, autocomplete dropdowns, text field underline caps |
| **Small** | `8.dp` | Chips, assistant badges, snackbars |
| **Medium** | `12.dp` – `16.dp` | Standard cards, alert dialogs, segmented buttons |
| **Large** | `24.dp` | Expressive cards, bottom sheets (top edge), navigation drawers |
| **Extra Large** | `32.dp` | Hero banners, floating search islands, prominent interactive cards |
| **Full / Pill** | `CircleShape` (`50%`) | Buttons, Floating Action Buttons, active indicator pills |

---

## 4. Typography Scale & Specifications

### 4.1 The 15 Expressive Type Tokens

| Token | Font Size (sp) | Line Height (sp) | Font Weight | Tracking (Letter Spacing) | Purpose |
| :--- | :--- | :--- | :--- | :--- | :--- |
| `displayLarge` | `57.sp` | `64.sp` | Bold (700) | `-0.25.sp` | High-impact numbers, hero splash words |
| `displayMedium` | `45.sp` | `52.sp` | Bold (700) | `0.0.sp` | Large screen hero headlines |
| `displaySmall` | `36.sp` | `44.sp` | SemiBold (600) | `0.0.sp` | Promotional banner anchors |
| `headlineLarge` | `32.sp` | `40.sp` | SemiBold (600) | `0.0.sp` | Major section headers |
| `headlineMedium`| `28.sp` | `36.sp` | SemiBold (600) | `0.0.sp` | Screen titles |
| `headlineSmall` | `24.sp` | `32.sp` | Medium (500) | `0.0.sp` | Dialog titles |
| `titleLarge` | `22.sp` | `28.sp` | SemiBold (600) | `0.0.sp` | Top app bar titles, card primary headers |
| `titleMedium` | `16.sp` | `24.sp` | Medium (500) | `+0.15.sp` | Subheaders, list item titles |
| `titleSmall` | `14.sp` | `20.sp` | Medium (500) | `+0.1.sp` | Secondary card titles |
| `bodyLarge` | `16.sp` | `24.sp` | Normal (400) | `+0.5.sp` | Sustained body reading, articles |
| `bodyMedium` | `14.sp` | `20.sp` | Normal (400) | `+0.25.sp` | Standard app UI reading text |
| `bodySmall` | `12.sp` | `16.sp` | Normal (400) | `+0.4.sp` | Helper captions, timestamps |
| `labelLarge` | `14.sp` | `20.sp` | SemiBold (600) | `+0.1.sp` | Primary buttons, navigation tabs |
| `labelMedium` | `12.sp` | `16.sp` | Medium (500) | `+0.5.sp` | Filter chips, tag labels |
| `labelSmall` | `11.sp` | `16.sp` | Medium (500) | `+0.5.sp` | Micro badges, status indicators |

---

## 5. Component Taxonomy & Behavioral Rules

### 5.1 Buttons Hierarchy
1. **Filled Button (`Button`):** Highest visual emphasis. Reserved for the 1 dominant action per screen (e.g. "Save", "Submit").
2. **Filled Tonal Button (`FilledTonalButton`):** High emphasis action using `secondaryContainer`. Used when a primary action already exists.
3. **Elevated Button (`ElevatedButton`):** Tonal elevation for cards resting on colored backgrounds.
4. **Outlined Button (`OutlinedButton`):** Medium emphasis. Used for secondary alternative actions (e.g. "Cancel", "Back").
5. **Text Button (`TextButton`):** Low emphasis for non-disruptive actions inside cards, dialogs, or toolbars.
6. **Split Button (`SplitButtonLayout`):** Expressive component combining a primary action button with an attached dropdown action.

### 5.2 Floating Action Buttons (FABs)
- **Small FAB (`SmallFloatingActionButton`):** `40.dp x 40.dp`. Compact screens or secondary floating actions.
- **Standard FAB (`FloatingActionButton`):** `56.dp x 56.dp`. The canonical primary screen action.
- **Large FAB (`LargeFloatingActionButton`):** `96.dp x 96.dp`. The signature M3 Expressive component. Large touch target, paired with `tertiaryContainer`.
- **Extended FAB (`ExtendedFloatingActionButton`):** Dynamically expands to show icon + text, collapsing to standard FAB on scroll.

### 5.3 Cards
- **Elevated Card (`ElevatedCard`):** Uses subtle tonal elevation.
- **Filled Card (`FilledCard`):** Uses `surfaceContainerHighest`.
- **Outlined Card (`OutlinedCard`):** 1dp border with `outlineVariant`.

---

## 6. Layout, Spacing & Adaptive Window Grid

### 6.1 Spacing Rhythm
- **Baseline Grid:** `4.dp`
- **Standard Increment Scale:** `4.dp`, `8.dp`, `12.dp`, `16.dp`, `24.dp`, `32.dp`, `48.dp`, `64.dp`.
- **Screen Margins:**
  - Compact: `16.dp`
  - Medium & Expanded: `24.dp`

### 6.2 Window Size Classes
```
  0dp ────────────── 600dp ────────────── 840dp ──────────────► Width
   [    COMPACT    ]    [    MEDIUM     ]    [   EXPANDED    ]
   Phones               Small Tablets        Large Tablets,
                        Foldables (unfolded) Desktop
   Nav: Bottom Bar      Nav: Navigation Rail Nav: Rail / Drawer
   Columns: 4           Columns: 8           Columns: 12
```

---

## 7. Machine & AI Ingestion Rules (LLM System Directives)

When an AI system is asked to produce Jetpack Compose code compliant with Material 3 Expressive, it MUST strictly adhere to the following rules:

1. **Never use hardcoded hex colors inside Composables:**
   Always reference `MaterialTheme.colorScheme.*` (e.g. `MaterialTheme.colorScheme.primaryContainer`).
2. **Never hardcode elevation shadows for surface depth:**
   Use the Surface Container tier (`surfaceContainerLowest`, `surfaceContainerLow`, `surfaceContainer`, `surfaceContainerHigh`, `surfaceContainerHighest`).
3. **Always use Modifier order correctly:**
   Modifiers wrap components from outer to inner.
   - Sizing (`fillMaxWidth()`, `size()`) -> Spacing (`padding()`) -> Interactions (`clickable()`) -> Visuals (`background()`, `clip()`).
4. **Always manage state with Unidirectional Data Flow (UDF):**
   - Use `StateFlow` in ViewModels.
   - Collect state in UI using `collectAsStateWithLifecycle()`.
   - Never write business logic inside Composable bodies.
5. **Enforce 60-120 FPS Native Performance:**
   - Hoist state outside loops.
   - Use `derivedStateOf` for derived calculations.
   - Never allocate objects inside `Canvas` or `drawWithContent` blocks.
