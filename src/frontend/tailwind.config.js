/** @type {import('tailwindcss').Config} */

module.exports = {
    content: [
        "../main/resources/templates/**/*.{html,js}",
        "../main/resources/static/**/*.{html,js}"
    ],
    darkMode: 'class',
    theme: {
        extend: {
            colors: {
                primary: {
                    DEFAULT: '#2563eb',        // blue-600
                    hover: '#1d4ed8',          // blue-700
                    focus: '#3b82f6',          // blue-500
                    active: '#1e40af',         // blue-800
                    dark: '#3b82f6',           // blue-500
                    'dark-hover': '#60a5fa',   // blue-400
                    'dark-focus': '#93c5fd',   // blue-300
                    'dark-active': '#1e3a8a',  // blue-900
                },
                secondary: {
                    DEFAULT: '#64748b',         // slate-500
                    hover: '#475569',           // slate-600
                    focus: '#94a3b8',           // slate-400
                    active: '#334155',          // slate-700
                    dark: '#94a3b8',            // slate-400
                    'dark-hover': '#cbd5e1',    // slate-300
                    'dark-focus': '#e2e8f0',    // slate-200
                    'dark-active': '#1e293b',   // slate-800
                },
                background: {
                    light: '#ffffff',
                    dark: '#1f2937',           // gray-800
                }
            }
        }
    }
}
