import { defineConfig } from 'vite';

export default defineConfig({
  build: {
    outDir: 'dist/static',
    lib: {
      entry: 'src/webswing-connector.ts',
      formats: ['es'],
      fileName: 'webswing-connector',
    },
    rollupOptions: {
      external: [],
      output: {
        globals: {},
      },
    },
  },
});
